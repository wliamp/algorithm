# Algorithm Bundle

Kotlin multi-module library with core criteria primitives, reactive adapters, and Spring Boot starters.

## Modules
`core`
Core criteria primitives, builders, and a lightweight `ImperativeQueue`.

`reactive`
Reactor-based adapters and async helpers for the core module.

`spring-boot-starter`
Spring Boot auto-configuration for imperative core components.

`spring-boot-starter-reactive`
Spring Boot auto-configuration for reactive adapters.

## Requirements
`Java 21` and `Gradle` (wrapper included).

## Build
```bash
./gradlew build
```

## Usage
### Core Criteria
```kotlin
import io.github.wliamp.agr.Criteria
import io.github.wliamp.agr.AndBuilder

data class User(val name: String, val age: Int)

val criteria = AndBuilder<User>()
    .startsWith(User::name, "A")
    .greaterThan(User::age, 18)
    .build()

val users = listOf(User("Alice", 22), User("Bob", 17))
val filtered = users.filter { criteria.matches(it) }
```

### Imperative Queue
```kotlin
import io.github.wliamp.agr.ImperativeQueue

val queue = ImperativeQueue<String>()
queue.enqueue("a")
queue.enqueue("b")
val head = queue.dequeue()
```

### Reactive Adapters
```kotlin
import io.github.wliamp.agr.reactive.asReactive

val reactiveQueue = queue.asReactive()
reactiveQueue.enqueue("c").subscribe()
```

## Publishing
Gradle publishing is configured for GitHub Packages and Sonatype. Provide the credentials via environment variables or Gradle properties.
