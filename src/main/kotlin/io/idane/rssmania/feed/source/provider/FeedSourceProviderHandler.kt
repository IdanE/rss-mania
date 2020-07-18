package io.idane.rssmania.feed.source.provider

import com.antelopesystem.crudframework.components.componentmap.ComponentMap
import com.antelopesystem.crudframework.crud.handler.CrudHandler
import io.idane.rssmania.feed.model.Feed
import io.idane.rssmania.feed.model.FeedItem
import io.idane.rssmania.feed.source.enums.FeedSourceType
import io.idane.rssmania.feed.source.model.FeedSource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class FeedSourceProviderHandler {

    val log = LoggerFactory.getLogger(FeedSourceProviderHandler::class.java)

    @Autowired
    private lateinit var crudHandler: CrudHandler

    @Autowired
    private lateinit var providers: List<FeedSourceProvider>

    @Scheduled(fixedDelay = 60000)
    fun fetchAllFeedItems() {
        val feeds = crudHandler.index(null, Feed::class.java)
                .execute()
                .data
        for (feed in feeds) {
            fetchFeedItems(feed)
        }
    }

    fun fetchFeedItems(feed: Feed, forceFetch: Boolean = false) {
        val items = mutableListOf<FeedItem>()
        log.info("Attempting to fetch items for feed [ ${feed.name} ] from [ ${feed.sources.size} ] sources")
        if(feed.sources.isEmpty()) {
            log.info("Feed [ ${feed.name} ] has no sources! Aborting fetch")
        }
        for (source in feed.sources) {
            log.info("Checking if source [ ${source.title} (${source.type}) ] of feed [ ${feed.name} ] should be fetched")
            if(!shouldFetchSource(source) && !forceFetch) {
                log.info("Source [ ${source.title} (${source.type}) ] of feed [ ${feed.name} ] should not be fetched at this time, continuing")
                continue
            }
            source.lastUpdate = Date()
            crudHandler.update(feed).execute()

            val provider = providers.find { it.type == source.type } ?: error("Provider of type [ ${source.type} ] not found")
            log.info("Provider for type ${source.type} found, attempoting to fetch items..")
            try {
                val providerItems = provider.fetch(source)
                log.info("${providerItems.size} items fetched from provider [ ${source.title} (${source.type}) ] of feed [ ${feed.name} ]")
            } catch(e: Exception) {
                log.info("Source [ ${source.title} (${source.type}) ] of feed [ ${feed.name} ] failed to fetch! Error: ${e.message}")
            }

        }

        var uniqueCount = 0

        for (item in items) {
            if(feed.items.find { it.externalId == item.externalId && item.sourceType == it.sourceType  } == null ) {
                feed.items.add(item)
                uniqueCount++
            }
        }
        log.info("${uniqueCount} items were added to the [ ${feed.name} ] feed")
        crudHandler.update(feed).execute()
    }

    private fun shouldFetchSource(source: FeedSource): Boolean {
        val lastUpdate = source.lastUpdate
        val refreshInterval = source.refreshInterval * 60L * 1000L
        if(lastUpdate != null) {
            return System.currentTimeMillis() - lastUpdate.time > refreshInterval
        }
        return true
    }

}