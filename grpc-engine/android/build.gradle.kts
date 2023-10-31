plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
}

android {
    compileSdk = libs.versions.android.target.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.min.get().toInt()
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_19
        sourceCompatibility = JavaVersion.VERSION_19
    }

    namespace = "com.cinematica.sdk.grpc.android"
}

dependencies {
    implementation(projects.grpcEngine)

    implementation(libs.grpc.android)
}

kotlin {
    jvmToolchain(19)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cinematica.sdk"
            artifactId = "grpc-android"
            version = "0.0.1"
            from(components["kotlin"])
        }
    }
    repositories {
        maven {
            setUrl("https://maven.pkg.jetbrains.space/vadymhrynyk/p/main/cinematica-library")
            credentials {
                username = project.findProperty("spaceUsername") as String
                password = project.findProperty("spacePassword") as String
            }
        }
    }
}