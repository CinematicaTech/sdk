plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("maven-publish")
}

kotlin {
    jvm()

    explicitApi()
}

group = "com.cinematica.sdk"

dependencies {
    commonMainImplementation(libs.kotlinx.datetime)
    commonMainImplementation(libs.kotlinx.coroutines)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cinematica"
            artifactId = "sdk"
            version = "0.0.1"
            from(components["kotlin"])
        }
    }
    repositories {
        maven {
            setUrl("https://maven.pkg.jetbrains.space/vadymhrynyk/p/main/cinematica-sdk")
            credentials {
                username = project.findProperty("spaceUsername") as String
                password = project.findProperty("spacePassword") as String
            }
        }
    }
}