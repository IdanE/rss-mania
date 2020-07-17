package io.idane.rssmania.feed.source.provider

import com.antelopesystem.crudframework.components.componentmap.ComponentMapKey
import io.idane.rssmania.feed.model.FeedItem
import io.idane.rssmania.feed.source.enums.FeedSourceType
import io.idane.rssmania.feed.source.model.FeedSource

interface FeedSourceProvider {
    @get:ComponentMapKey
    val type: FeedSourceType

    fun fetch(source: FeedSource): List<FeedItem>
}