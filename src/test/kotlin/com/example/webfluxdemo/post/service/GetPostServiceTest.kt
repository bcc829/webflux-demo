package com.example.webfluxdemo.post.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@SpringBootTest
@ExtendWith(SpringExtension::class)
class GetPostServiceTest {

    @Autowired
    lateinit var getPostService: GetPostService

    @Test
    fun getPostsTest() {
        getPostService.getPosts().blockLast()
        assert(true)
    }

    @Test
    fun getFiveLatestPostsTest() {
        val posts = getPostService.getFiveLatestPosts()

        assertEquals(posts.collectList().block()?.size, 5)
    }

    @Test
    fun getPostById() {
        getPostService.getPostByIdWithAddReadCount(1).block()
        assert(true)
    }

}