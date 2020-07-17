package io.idane.rssmania.feed.source.provider

import com.antelopesystem.crudframework.components.componentmap.ComponentMap
import com.antelopesystem.crudframework.crud.handler.CrudHandler
import io.idane.rssmania.feed.model.Feed
import io.idane.rssmania.feed.model.FeedItem
import io.idane.rssmania.feed.source.enums.FeedSourceType
import io.idane.rssmania.feed.source.model.FeedSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class FeedSourceProviderHandler {

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
        for (source in feed.sources) {
            if(!shouldFetchSource(source) && !forceFetch) {
                continue
            }
            source.lastUpdate = Date()
            crudHandler.update(feed).execute()

            val provider = providers.find { it.type == source.type } ?: error("Provider of type [ ${source.type} ] not found")
            items += provider.fetch(source)
        }
        for (item in items) {
            if(feed.items.find { it.externalId == item.externalId && item.sourceType == it.sourceType  } == null ) {
                feed.items.add(item)
            }
        }
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