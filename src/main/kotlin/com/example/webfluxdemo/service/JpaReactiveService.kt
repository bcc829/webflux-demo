package com.example.webfluxdemo.service

import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import java.util.concurrent.Callable


open class JpaReactiveService(private val scheduler: Scheduler) {

    fun <T> async(callable: Callable<T>): Mono<T> {
        return Mono.fromCallable(callable).publishOn(scheduler).log()
    }

}