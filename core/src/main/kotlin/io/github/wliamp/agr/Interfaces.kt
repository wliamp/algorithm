package io.github.wliamp.agr

interface ICriteria<T> {
    fun matches(target: T): Boolean
}
