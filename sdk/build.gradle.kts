plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    id("maven-publish")
}

kotlin {
    jvm()
    jvmToolchain(17)

    explicitApi()
}

group = "com.cinematica.sdk.authorization"

dependencies {
    commonMainImplementation(libs.kotlinx.datetime)
    commonMainImplementation(libs.kotlinx.coroutines)
}