import java.lang.System.getenv

rootProject.name = "algorithm"

getenv("MODULE")?.takeIf { it.isNotBlank() }?.let {
    include(it)
} ?: include(
    "core",
    "reactive",
    "spring-boot-starter",
    "spring-boot-starter-reactive",
)
