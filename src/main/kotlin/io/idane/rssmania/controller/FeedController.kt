package io.idane.rssmania.controller

import com.antelopesystem.crudframework.web.controller.BaseCRUDController
import com.antelopesystem.crudframework.web.ro.ResultRO
import io.idane.rssmania.feed.FeedService
import io.idane.rssmania.feed.model.Feed
import io.idane.rssmania.feed.ro.FeedRO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/feeds")
class FeedController : BaseCRUDController<String, Feed, FeedRO>() {

    @Autowired
    private lateinit var feedService: FeedService

    @GetMapping("by-name/{name}")
    fun getFeedByName(@PathVariable name: String): ResultRO<*>? {
        return wrapResult { return@wrapResult feedService.getFeedROByName(name) }
    }
}