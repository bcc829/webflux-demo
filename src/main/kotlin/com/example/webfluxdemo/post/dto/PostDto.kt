package com.example.webfluxdemo.post.dto

import java.time.LocalDateTime

data class PostDto(
        var seqId: Long? = null,
        var title: String? = null,
        var content: String? = null,
        var regDate: LocalDateTime? = null,
        var updateDate: LocalDateTime? = null,
        var readCount: Int? = null
)