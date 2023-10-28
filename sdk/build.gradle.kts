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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cinematica.sdk"
            artifactId = "core"
            version = "0.0.1"
            from(components["kotlin"])
        }
    }
    repositories {
        maven {
            setUrl("https://maven.pkg.jetbrains.space/vadymhrynyk/p/main/cinematica")
            credentials {
                username = project.findProperty("spaceUsername") as String
                password = project.findProperty("spacePassword") as String
            }
        }
    }
}