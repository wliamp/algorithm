import java.lang.System.getenv

rootProject.name = "algorithm"

val moduleDeps = mapOf(
    "core" to emptyList(),
    "reactive" to listOf("core"),
    "spring-boot-starter" to listOf("core"),
    "spring-boot-starter-reactive" to listOf("reactive"),
)

val included = mutableSetOf<String>()

fun includeRecursive(module: String) =
    module
        .takeIf { included.add(it) }
        ?.also { include(it) }
        ?.let { moduleDeps[it].orEmpty().forEach(::includeRecursive) }

getenv("MODULE")?.takeIf { it.isNotBlank() }?.let {
    includeRecursive(it)
} ?: include(
    "core",
    "reactive",
    "spring-boot-starter",
    "spring-boot-starter-reactive",
)
