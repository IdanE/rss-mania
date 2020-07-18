package io.idane.rssmania.feed.ro

import com.antelopesystem.crudframework.fieldmapper.annotation.DefaultMappingTarget
import com.antelopesystem.crudframework.fieldmapper.annotation.MappedField
import com.antelopesystem.crudframework.mongo.ro.BaseMongoRO
import io.idane.rssmania.feed.model.Feed
import io.idane.rssmania.feed.source.model.FeedSource

@DefaultMappingTarget(Feed::class)
data class FeedRO(
        @MappedField
        var name: String? = null,
        @MappedField
        var title: String? = null,
        @MappedField
        var description: String? = null,
        @MappedField
        var sources: MutableList<FeedSource>? = null
) : BaseMongoRO()