package io.github.wliamp.agr

internal class EqualsCriteria<T, V>(
    private val selector: (T) -> V,
    private val expected: V
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        selector(target) == expected
}

internal class RangeCriteria<T : Comparable<T>>(
    private val selector: (T) -> T,
    private val min: T,
    private val max: T
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        selector(target) in min..max
}

internal class GreaterThanCriteria<T, V : Comparable<V>>(
    private val selector: (T) -> V,
    private val threshold: V
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        selector(target) > threshold
}

internal class LessThanCriteria<T, V : Comparable<V>>(
    private val selector: (T) -> V,
    private val threshold: V
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        selector(target) < threshold
}

internal class ContainsCriteria<T, E>(
    private val selector: (T) -> Collection<E>,
    private val expected: E
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        expected in selector(target)
}

internal class StartsWithCriteria<T>(
    private val selector: (T) -> String,
    private val prefix: String
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        selector(target).startsWith(prefix)
}

internal class EndsWithCriteria<T>(
    private val selector: (T) -> String,
    private val suffix: String
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        selector(target).endsWith(suffix)
}

internal class CustomCriteria<T>(
    private val predicate: (T) -> Boolean
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        predicate(target)
}

internal class AndCriteria<T>(
    private val left: ICriteria<T>,
    private val right: ICriteria<T>
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        left.matches(target) && right.matches(target)
}

internal class OrCriteria<T>(
    private val left: ICriteria<T>,
    private val right: ICriteria<T>
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        left.matches(target) || right.matches(target)
}

internal class NotCriteria<T>(
    private val inner: ICriteria<T>
) : ICriteria<T> {
    override fun matches(target: T): Boolean =
        !inner.matches(target)
}
