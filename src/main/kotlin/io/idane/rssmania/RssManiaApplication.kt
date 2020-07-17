package io.idane.rssmania

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RssManiaApplication

fun main(args: Array<String>) {
    runApplication<RssManiaApplication>(*args)
}
