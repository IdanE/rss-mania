package io.idane.rssmania

import com.antelopesystem.crudframework.mongo.annotation.EnableMongoCrud
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableMongoCrud
@EnableScheduling
class RssManiaApplication
fun main(args: Array<String>) {
    runApplication<RssManiaApplication>(*args)
}
