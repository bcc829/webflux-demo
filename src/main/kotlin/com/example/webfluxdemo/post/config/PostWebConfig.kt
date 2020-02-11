package com.example.webfluxdemo.post.config

import com.example.webfluxdemo.post.handler.PostHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
@EnableWebFlux
class PostWebConfig(private val handler: PostHandler) {

    @Bean
    fun routerFunction() =
            nest(path("/post"), router {
                listOf(
                        GET("/", handler::getPosts),
                        GET("/latest", handler::getPostsLatest),
                        GET("/{id}", handler::getPostByIdWithAddReadCount)

                )
            })

}
