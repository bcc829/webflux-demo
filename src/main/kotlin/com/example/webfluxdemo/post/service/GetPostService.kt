package com.example.webfluxdemo.post.service

import com.example.webfluxdemo.post.domain.entity.Post
import com.example.webfluxdemo.post.repository.PostRepository
import com.example.webfluxdemo.service.JpaReactiveService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import java.util.*
import java.util.concurrent.Callable

@Service
class GetPostService(
        private val postRepository: PostRepository,
        @Qualifier("jdbcScheduler") private val scheduler: Scheduler
) : JpaReactiveService(scheduler) {

    fun getPosts(): Mono<MutableIterable<Post>> {
        return async(Callable { postRepository.findAll() })

    }

    fun getPostById(id: Long): Mono<Optional<Post>> {
        return async(Callable { postRepository.findById(id) })
    }
}