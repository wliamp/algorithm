package io.github.wliamp.kit.agr.spring.reactive

import io.github.wliamp.kit.agr.core.Queue
import io.github.wliamp.kit.agr.reactive.ReactiveQueueAdapter
import io.github.wliamp.kit.agr.reactive.asReactive
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean

@AutoConfiguration
class AlgorithmReactiveAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    fun imperativeQueue(): Queue<Any> = Queue()

    @Bean
    @ConditionalOnMissingBean
    fun reactiveQueueAdapter(queue: Queue<Any>): ReactiveQueueAdapter<Any> =
        queue.asReactive()
}