package io.github.wliamp.kit.agr.core

interface ICriteria<T> {
    fun matches(target: T): Boolean
}
