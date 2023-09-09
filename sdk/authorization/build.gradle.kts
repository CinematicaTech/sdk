plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
    id("maven-publish")
}

//kotlin {
//    jvm()
//
//    explicitApi()
//}

group = "com.cinematica.sdk.authorization"

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.logging)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("com.github.skydoves:retrofit-adapters-result:1.0.7")
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