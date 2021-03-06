package io.idane.rssmania.feed

import com.antelopesystem.crudframework.crud.handler.CrudHandler
import com.antelopesystem.crudframework.modelfilter.dsl.where
import com.rometools.rome.feed.rss.*
import io.idane.rssmania.feed.model.Feed
import io.idane.rssmania.feed.ro.FeedRO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.servlet.View
import org.springframework.web.servlet.view.feed.AbstractRssFeedView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class FeedService {
    @Autowired
    private lateinit var crudHandler: CrudHandler

    fun getFeedROByName(name: String): FeedRO {
        return crudHandler.getRO(getFeedByName(name), FeedRO::class.java)
    }

    fun getFeedByName(name: String): Feed {
        return crudHandler.showBy(where {
            "name" Equal name
        }, Feed::class.java)
                .execute() ?: error("Feed $name not found")
    }

    fun getFeedView(name: String): View {
        val feed = getFeedByName(name)
        return object : AbstractRssFeedView() {
            override fun buildFeedMetadata(model: MutableMap<String, Any>, channel: Channel, request: HttpServletRequest) {
                channel.title = feed.title
                channel.description = feed.description
                channel.link = "http://google.com"
            }

            override fun buildFeedItems(model: MutableMap<String, Any>, request: HttpServletRequest, response: HttpServletResponse): MutableList<Item> {
                val items = mutableListOf<Item>()
                for (item in feed.items) {
                    val entry = Item().apply {
                        title = item.title
                        source = Source().apply {
                            value = item.source
                        }
                        description = Description().apply {
                            value = item.text
                        }
                        author = item.source // Temporary since we don't use source
                        link = item.url
                        guid = Guid().apply {
                            value = item.guid
                        }
                        pubDate = item.publicationDate
                    }
                    items += entry
                }
                return items
            }
        }
    }


}