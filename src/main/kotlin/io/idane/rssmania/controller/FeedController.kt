package io.idane.rssmania.controller

import io.idane.rssmania.feed.FeedService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.View

@RestController
class FeedController {
    @Autowired
    private lateinit var feedService: FeedService

    @GetMapping("/{name}")
    fun getFeed(@PathVariable name: String): View {
        return feedService.getFeedView(name)
    }

}