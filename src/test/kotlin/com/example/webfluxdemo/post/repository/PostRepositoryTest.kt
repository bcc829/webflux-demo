package com.example.webfluxdemo.post.repository

import com.example.webfluxdemo.post.domain.entity.Post
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@SpringBootTest
@ExtendWith(SpringExtension::class)
class PostRepositoryTest {

    @Autowired
    lateinit var postRepository: PostRepository

    @Test
    @Order(value = 1)
    fun postRepositorySaveTest() {
        postRepository.save(Post(title = "!@#$#%#$", content = "contents"))

        assert(true)
    }

    @Test
    @Order(value = 2)
    fun postRepositoryGetPostsByTitleTest() {
        val posts = postRepository.getPostsByTitleContains("!@#$#%#$")

        assertTrue(posts.isNotEmpty())
    }

    @Test
    @Order(value = 3)
    fun postRepositoryDeleteTest() {
        val posts = postRepository.getPostsByTitleContains("!@#$#%#$")

        posts.forEach {
            postRepository.delete(it)
        }

        assert(true)
    }
}