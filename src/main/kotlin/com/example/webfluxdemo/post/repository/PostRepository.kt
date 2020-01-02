package com.example.webfluxdemo.post.repository

import com.example.webfluxdemo.post.domain.entity.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository: CrudRepository<Post, Long> {
    fun getPostsByTitleEquals(title: String): List<Post>
}