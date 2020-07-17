package io.idane.rssmania.feed.source.model

import io.idane.rssmania.feed.source.enums.FeedSourceType
import java.util.*

class FeedSource(
        var title: String,
        var type: FeedSourceType,
        var refreshInterval: Int = 10,
        var parameters: MutableMap<String, Any?> = mutableMapOf(),
        var username: String? = null,
        var password: String? = null,
        var lastUpdate: Date? = null
) {
    operator fun get(value: String): Any? {
        return parameters[value]
    }
}