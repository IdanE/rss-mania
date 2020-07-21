package io.idane.rssmania.feed.source.provider.twitter

import com.google.gson.GsonBuilder
import com.mashape.unirest.http.Unirest
import io.idane.rssmania.feed.model.FeedItem
import io.idane.rssmania.feed.source.enums.FeedSourceType
import io.idane.rssmania.feed.source.model.FeedSource
import io.idane.rssmania.feed.source.provider.AbstractFeedSourceProvider
import io.idane.rssmania.feed.source.provider.twitter.model.Tweet
import io.idane.rssmania.utils.fromJson
import org.json.JSONException
import org.springframework.stereotype.Component

private const val BASE_URL = "https://api.twitter.com"

private object PARAMETERS {
    const val USER = "user"
}

@Component
class TwitterProvider : AbstractFeedSourceProvider() {
    override val type: FeedSourceType = FeedSourceType.Twitter

    override fun fetch(source: FeedSource): List<FeedItem> {
        val feedItems = mutableListOf<FeedItem>()
        val token = getAccessToken(getUsername(source), getPassword(source))
        val responseString = Unirest.get("$BASE_URL/1.1/statuses/user_timeline.json")
                .queryString("screen_name", getUser(source))
                .queryString("count", 25)
                .header("Authorization", "Bearer $token")
                .asString()
                .body
        val tweets = gson.fromJson<List<Tweet>>(responseString)

        for (tweet in tweets) {
            feedItems += FeedItem(
                    tweet.idStr,
                    FeedSourceType.Twitter,
                    source.title,
                    getUser(source),
                    getUser(source),
                    tweet.text,
                    tweet.text,
                    "",
                    tweet.createdAt
            )
        }
        return feedItems
    }
    private fun getAccessToken(username: String, password: String): String {
        val response = Unirest
                .post("$BASE_URL/oauth2/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("grant_type", "client_credentials")
                .basicAuth(username, password)
                .asJson()
        try {
            return response.body.`object`["access_token"] as String
        } catch(e: JSONException) {
            error("Did not receive access token from Twitter")
        }
    }

    fun getUser(source: FeedSource): String {
        return source[PARAMETERS.USER] as String? ?: error("Twitter User is not set")
    }

    companion object {
        private val gson = GsonBuilder().setDateFormat("EEE MMM dd HH:mm:ss Z yyyy").create()
    }

}