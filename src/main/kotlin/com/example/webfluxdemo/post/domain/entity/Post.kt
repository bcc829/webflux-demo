package com.example.webfluxdemo.post.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(schema = "public", name = "post")
data class Post(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name = "seq_id")
        val seqId: Long? = null,

        @Column(name = "title")
        var title: String,

        @Column(name = "content", columnDefinition = "text")
        var content: String,

        @Column(name = "reg_date")
        @CreatedDate
        var regDate: LocalDateTime? = null,

        @Column(name = "upd_date")
        @LastModifiedDate
        var updateDate: LocalDateTime? = null,

        @Column(name = "read_count")
        var readCount: Int? = 0
)