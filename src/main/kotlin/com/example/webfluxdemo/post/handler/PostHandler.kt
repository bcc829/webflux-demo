package com.example.webfluxdemo.post.handler

import com.example.webfluxdemo.post.domain.entity.Post
import com.example.webfluxdemo.post.service.GetPostService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class PostHandler(private val getPostService: GetPostService) {

    fun getPosts(request: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok().body(getPostService.getPosts(), Post::class.java)

    fun getPostById(request: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok().body(
                    getPostService.getPostById(id = request.pathVariable("id").toLong()),
                    Post::class.java
            )
}