package com.example.webfluxdemo.post.service

import com.example.webfluxdemo.post.dto.PostDto
import com.example.webfluxdemo.post.repository.PostRepository
import com.example.webfluxdemo.service.JpaReactiveService
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import java.util.concurrent.Callable

@Service
class GetPostService(
        private val postRepository: PostRepository,
        @Qualifier("jdbcScheduler") private val scheduler: Scheduler,
        private val modelMapper: ModelMapper
) : JpaReactiveService(scheduler) {

    fun getPosts(): Flux<PostDto> =
            async(Callable {
                postRepository.getAllByOrderByRegDateDesc()
                        .map { modelMapper.map(it, PostDto::class.java) }
            })
                    .flatMapMany { Flux.fromIterable(it) }


    fun getFiveLatestPosts(): Flux<PostDto> =
            async(Callable {
                postRepository.findTop5ByOrderByRegDateDesc()
                        .map { modelMapper.map(it, PostDto::class.java) }
            })
                    .flatMapMany { Flux.fromIterable(it) }


    fun getPostByIdWithAddReadCount(id: Long): Mono<PostDto> =
            async(Callable {
                val optionalPost = postRepository.findById(id)

                if (optionalPost.isPresent) {
                    val post = optionalPost.get()
                    post.readCount = post.readCount?.plus(1)
                    postRepository.save(post)
                }

                optionalPost
            })
                    .flatMap { optional ->
                        optional.map { Mono.just(modelMapper.map(it, PostDto::class.java)) }
                                .orElseGet { Mono.empty() }
                    }
}