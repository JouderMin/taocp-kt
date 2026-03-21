plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kover)
}

group = "io.github.joudermin"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        setUrl("https://maven.aliyun.com/repository/public/")
    }
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.logging.jvm)
    implementation(libs.slf4j.api)
    implementation(libs.logback.classic)

    testImplementation(platform(libs.junit.bom))
    testImplementation(platform(libs.assertj.bom))
    testImplementation(platform(libs.mockito.bom))

    testImplementation(libs.kotlin.test)
    testImplementation(libs.assertj.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit.jupiter)
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}