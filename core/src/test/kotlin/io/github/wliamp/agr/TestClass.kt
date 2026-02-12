package io.github.wliamp.agr

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
class TestClass {
    @Test
    fun alwaysTrueMatches() {
        val criteria = Criteria.alwaysTrue<String>()
        assertTrue(criteria.matches("ok"))
    }
}
