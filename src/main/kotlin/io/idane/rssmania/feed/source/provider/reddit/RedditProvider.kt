package io.idane.rssmania.feed.source.provider.reddit

import com.google.gson.Gson
import com.mashape.unirest.http.Unirest
import io.idane.rssmania.feed.model.FeedItem
import io.idane.rssmania.feed.source.enums.FeedSourceType
import io.idane.rssmania.feed.source.model.FeedSource
import io.idane.rssmania.feed.source.provider.FeedSourceProvider
import io.idane.rssmania.feed.source.provider.reddit.model.RedditChild
import io.idane.rssmania.feed.source.provider.reddit.model.RedditResponse
import org.springframework.stereotype.Component
import java.util.*


private const val BASE_URL = "https://www.reddit.com"

private object PARAMETERS {
    const val SUBREDDIT = "subreddit"
}

@Component
class RedditProvider : FeedSourceProvider {
    override val type: FeedSourceType = FeedSourceType.Reddit

    override fun fetch(source: FeedSource): List<FeedItem> {
        val posts = getSubredditPosts(getSubreddit(source))
        val feedItems = mutableListOf<FeedItem>()
        for (post in posts) {
            feedItems += FeedItem(
                    post.data.id,
                    FeedSourceType.Reddit,
                    source.title,
                    "/u/" + post.data.author,
                    post.data.subredditNamePrefixed,
                    post.data.title,
                    if (post.data.isSelf) post.data.selfText else "", // todo: summary
                    post.data.url,
                    Date(post.data.createdUtc * 1000L),
                    imageUrl = post.data.thumbnail
            )
        }
        return feedItems
    }

    fun getSubredditPosts(subreddit: String): MutableList<RedditChild> {
        val responseString = Unirest.get("$BASE_URL/r/$subreddit/new.json")
                .header("User-Agent", "jvm:rssmania:1.0.0")
                .asString().body
        return Gson().fromJson(responseString, RedditResponse::class.java).data.children
    }

    private fun getSubreddit(source: FeedSource): String {
        val subreddit = source[PARAMETERS.SUBREDDIT] ?: throw RuntimeException("Reddit Subreddit is not set")
        return subreddit.toString()
    }
}