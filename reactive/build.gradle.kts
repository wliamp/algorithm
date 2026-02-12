plugins {
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
    id("signing")
}

dependencies {
    api(project(":core"))
    api("io.projectreactor:reactor-core")
    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.2"))
}
