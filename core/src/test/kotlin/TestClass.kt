package io.github.wliamp.kit.agr.core

import io.github.wliamp.kit.agr.core.Criteria.alwaysTrue
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestClass {
    @Test
    fun alwaysTrueMatches() {
        assertTrue(alwaysTrue<String>().matches("ok"))
    }
}