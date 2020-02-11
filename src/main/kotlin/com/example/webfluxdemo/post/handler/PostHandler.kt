package com.example.webfluxdemo.post.handler

import com.example.webfluxdemo.post.dto.PostDto
import com.example.webfluxdemo.post.service.GetPostService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class PostHandler(private val getPostService: GetPostService) {

    fun getPosts(request: ServerRequest): Mono<ServerResponse> =
            ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(getPostService.getPosts(), PostDto::class.java)

    fun getPostsLatest(request: ServerRequest): Mono<ServerResponse> =
            ok()
                    .body(getPostService.getFiveLatestPosts(), PostDto::class.java)


    fun getPostByIdWithAddReadCount(request: ServerRequest): Mono<ServerResponse> =
            getPostService
                    .getPostByIdWithAddReadCount(request.pathVariable("id")
                            .toLong())
                    .flatMap { ok().body(Mono.just(it), PostDto::class.java) }
                    .switchIfEmpty(notFound().build())


}