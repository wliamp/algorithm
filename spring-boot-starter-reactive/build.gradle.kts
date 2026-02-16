plugins {
    id("org.jetbrains.kotlin.plugin.spring")
    id("java-library")
}

dependencies {
    api(project(":reactive"))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.2"))
    implementation("org.springframework.boot:spring-boot-autoconfigure")
}
