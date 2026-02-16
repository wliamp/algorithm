package io.github.wliamp.kit.agr.reactive

import io.github.wliamp.kit.agr.core.AndBuilder
import io.github.wliamp.kit.agr.core.Criteria.filter
import io.github.wliamp.kit.agr.core.ICriteria
import io.github.wliamp.kit.agr.core.NotAndBuilder
import io.github.wliamp.kit.agr.core.Queue
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.*

fun <T> ICriteria<T>.matchesAsync(target: T): Mono<Boolean> =
    fromSupplier { matches(target) }

fun <T> List<T>.filterAsync(criteria: List<ICriteria<T>>): Mono<List<T>> =
    fromSupplier { filter(this, criteria) }

fun <T> List<T>.filterAsync(criteria: ICriteria<T>): Mono<List<T>> =
    fromSupplier { filter(this, listOf(criteria)) }

fun <T> AndBuilder<T>.filterAsync(items: List<T>): Mono<List<T>> =
    fromSupplier { filter(items) }

fun <T> NotAndBuilder<T>.filterAsync(items: List<T>): Mono<List<T>> =
    fromSupplier { filter(items) }

class ReactiveQueueAdapter<T : Any>(
    private val delegate: Queue<T>
) {
    fun enqueue(item: T): Mono<Boolean> =
        fromSupplier { delegate.enqueue(item) }

    fun enqueueAll(items: Collection<T>): Mono<Boolean> =
        fromSupplier { delegate.enqueueAll(items) }

    fun dequeue(): Mono<T> =
        fromSupplier { delegate.dequeue() }
            .flatMap { justOrEmpty(it) }

    fun peek(): Mono<T> =
        fromSupplier { delegate.peek() }
            .flatMap { justOrEmpty(it) }

    fun size(): Mono<Int> =
        fromSupplier { delegate.size() }

    fun isEmpty(): Mono<Boolean> =
        fromSupplier { delegate.isEmpty() }

    fun contains(item: T): Mono<Boolean> =
        fromSupplier { delegate.contains(item) }

    fun toList(): Mono<List<T>> =
        fromSupplier { delegate.toList() }

    fun filter(criteria: List<ICriteria<T>>): Mono<List<T>> =
        fromSupplier { delegate.filter(criteria) }

    fun filter(criteria: ICriteria<T>): Mono<List<T>> =
        fromSupplier { delegate.filter(criteria) }

    fun dequeueBy(criteria: ICriteria<T>): Mono<T> =
        fromSupplier { delegate.dequeueBy(criteria) }
            .flatMap { justOrEmpty(it) }

    fun removeBy(criteria: ICriteria<T>): Mono<Boolean> =
        fromSupplier { delegate.removeBy(criteria) }

    fun clear(): Mono<Void> =
        fromRunnable { delegate.clear() }
}

fun <T : Any> Queue<T>.asReactive(): ReactiveQueueAdapter<T> =
    ReactiveQueueAdapter(this)
