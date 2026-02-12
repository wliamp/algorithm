package io.github.wliamp.agr.spring

import io.github.wliamp.agr.Queue
import io.github.wliamp.agr.reactive.ReactiveQueueAdapter
import io.github.wliamp.agr.reactive.asReactive
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
