package io.idane.rssmania.feed.source.provider.rss

import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import io.idane.rssmania.feed.model.FeedItem
import io.idane.rssmania.feed.source.enums.FeedSourceType
import io.idane.rssmania.feed.source.model.FeedSource
import io.idane.rssmania.feed.source.provider.FeedSourceProvider
import org.springframework.stereotype.Component
import java.net.URL

private object PARAMETERS {
    const val FEED_URL = "feedUrl"
}

@Component
class RssProvider : FeedSourceProvider {

    override val type: FeedSourceType = FeedSourceType.Rss

    override fun fetch(source: FeedSource): List<FeedItem> {
        val feed = SyndFeedInput().build(XmlReader(URL(source[PARAMETERS.FEED_URL].toString())))
        val items = mutableListOf<FeedItem>()
        for (entry in feed.entries) {
            items += FeedItem(
                    entry.uri,
                    FeedSourceType.Rss,
                    source.title,
                    entry.author,
                    feed.title,
                    entry.title,
                    entry.description.value,
                    entry.link,
                    entry.publishedDate
            )
        }
        return items
    }
}