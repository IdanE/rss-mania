package io.idane.rssmania.feed.model

import io.idane.rssmania.feed.source.enums.FeedSourceType
import java.util.*

data class FeedItem(
        var externalId: String,
        var sourceType: FeedSourceType,
        var source: String,
        var originalAuthor: String,
        var originalLocation: String,
        var title: String,
        var text: String,
        var url: String,
        var publicationDate: Date,
        var creationDate: Date = Date(),
        var imageUrl: String = "",
        var tags: MutableList<String> = mutableListOf()
) {
    var guid: String = UUID.randomUUID().toString()
}