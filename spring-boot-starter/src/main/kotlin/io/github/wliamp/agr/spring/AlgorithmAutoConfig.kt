package io.github.wliamp.agr.spring

import io.github.wliamp.agr.Queue
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean

@AutoConfiguration
class AlgorithmAutoConfig {
    @Bean
    @ConditionalOnMissingBean
    fun reactiveQueue(): Queue<Any> = Queue()
}
