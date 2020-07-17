package io.idane.rssmania.feed.model

import com.antelopesystem.crudframework.fieldmapper.annotation.MappedField
import com.antelopesystem.crudframework.mongo.model.BaseMongoEntity
import com.antelopesystem.crudframework.mongo.model.BaseMongoUpdateableEntity
import io.idane.rssmania.feed.source.model.FeedSource
import org.springframework.data.mongodb.core.mapping.Document


@Document
data class Feed(
        @MappedField
        var name: String,
        @MappedField
        var title: String,
        @MappedField
        var description: String,
        @MappedField
        var sources: MutableList<FeedSource> = mutableListOf(),
        @MappedField
        var items: MutableList<FeedItem> = mutableListOf()
) : BaseMongoEntity()