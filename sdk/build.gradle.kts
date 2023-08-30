plugins {
    alias(libs.plugins.kotlin.multiplatform)
    // alias(libs.plugins.library.publish)
    id("me.him188.maven-central-publish") version "1.0.0-dev-1"
}

kotlin {
    jvm()

    explicitApi()
}

group = "com.cinematica.backend"

dependencies {
    commonMainImplementation(libs.kotlinx.datetime)
    commonMainImplementation(libs.kotlinx.coroutines)
}

mavenCentralPublish {
    singleDevGithubProject("sliderzxc", "maven-central-publish")
    licenseApacheV2()
}