package io.idane.rssmania.feed.source.provider.twitter.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Tweet(
        @SerializedName("id_str")
        val idStr: String,
        val text: String,
        @SerializedName("created_at")
        val createdAt: Date
)