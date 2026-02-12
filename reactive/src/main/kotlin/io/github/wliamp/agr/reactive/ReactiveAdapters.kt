package io.github.wliamp.agr.reactive

import io.github.wliamp.agr.AndBuilder
import io.github.wliamp.agr.Criteria
import io.github.wliamp.agr.ICriteria
import io.github.wliamp.agr.NotAndBuilder
import io.github.wliamp.agr.Queue
import reactor.core.publisher.Mono

fun <T> ICriteria<T>.matchesAsync(target: T): Mono<Boolean> =
    Mono.fromSupplier { matches(target) }

fun <T> List<T>.filterAsync(criteria: List<ICriteria<T>>): Mono<List<T>> =
    Mono.fromSupplier { Criteria.filter(this, criteria) }

fun <T> List<T>.filterAsync(criteria: ICriteria<T>): Mono<List<T>> =
    Mono.fromSupplier { Criteria.filter(this, listOf(criteria)) }

fun <T> AndBuilder<T>.filterAsync(items: List<T>): Mono<List<T>> =
    Mono.fromSupplier { filter(items) }

fun <T> NotAndBuilder<T>.filterAsync(items: List<T>): Mono<List<T>> =
    Mono.fromSupplier { filter(items) }

class ReactiveQueueAdapter<T : Any>(
    private val delegate: Queue<T>
) {
    fun enqueue(item: T): Mono<Boolean> =
        Mono.fromSupplier { delegate.enqueue(item) }

    fun enqueueAll(items: Collection<T>): Mono<Boolean> =
        Mono.fromSupplier { delegate.enqueueAll(items) }

    fun dequeue(): Mono<T> =
        Mono.fromSupplier { delegate.dequeue() }
            .flatMap { Mono.justOrEmpty(it) }

    fun peek(): Mono<T> =
        Mono.fromSupplier { delegate.peek() }
            .flatMap { Mono.justOrEmpty(it) }

    fun size(): Mono<Int> =
        Mono.fromSupplier { delegate.size() }

    fun isEmpty(): Mono<Boolean> =
        Mono.fromSupplier { delegate.isEmpty() }

    fun contains(item: T): Mono<Boolean> =
        Mono.fromSupplier { delegate.contains(item) }

    fun toList(): Mono<List<T>> =
        Mono.fromSupplier { delegate.toList() }

    fun filter(criteria: List<ICriteria<T>>): Mono<List<T>> =
        Mono.fromSupplier { delegate.filter(criteria) }

    fun filter(criteria: ICriteria<T>): Mono<List<T>> =
        Mono.fromSupplier { delegate.filter(criteria) }

    fun dequeueBy(criteria: ICriteria<T>): Mono<T> =
        Mono.fromSupplier { delegate.dequeueBy(criteria) }
            .flatMap { Mono.justOrEmpty(it) }

    fun removeBy(criteria: ICriteria<T>): Mono<Boolean> =
        Mono.fromSupplier { delegate.removeBy(criteria) }

    fun clear(): Mono<Void> =
        Mono.fromRunnable { delegate.clear() }
}

fun <T : Any> Queue<T>.asReactive(): ReactiveQueueAdapter<T> =
    ReactiveQueueAdapter(this)
