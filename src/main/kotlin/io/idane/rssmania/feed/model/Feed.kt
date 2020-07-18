package io.idane.rssmania.feed.model

import com.antelopesystem.crudframework.crud.annotation.Deleteable
import com.antelopesystem.crudframework.fieldmapper.annotation.DefaultMappingTarget
import com.antelopesystem.crudframework.fieldmapper.annotation.MappedField
import com.antelopesystem.crudframework.mongo.model.BaseMongoEntity
import io.idane.rssmania.feed.ro.FeedRO
import io.idane.rssmania.feed.source.model.FeedSource
import org.springframework.data.mongodb.core.mapping.Document


@Document
@DefaultMappingTarget(FeedRO::class)
@Deleteable(softDelete = false)
data class Feed(
        @MappedField
        var name: String = "",
        @MappedField
        var title: String = "",
        @MappedField
        var description: String = "",
        @MappedField
        var sources: MutableList<FeedSource> = mutableListOf(),
        var items: MutableList<FeedItem> = mutableListOf()
) : BaseMongoEntity()