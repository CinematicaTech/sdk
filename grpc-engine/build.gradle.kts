import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.projects
import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.google.protobuf)
    //`maven-publish`
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.grpc.protobuf)
    implementation(libs.grpc.netty)
    implementation(libs.protobuf.kotlin)
    implementation(libs.protobuf.java)

    implementation(libs.kotlinx.datetime)
    implementation(projects.sdk)
}

kotlin {
    explicitApi()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.22.2"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.54.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

//publishing {
//    publications {
//        create<MavenPublication>("maven") {
//            groupId = "com.cinematica.sdk"
//            artifactId = "grpc-engine"
//            version = "0.0.1"
//            from(components["kotlin"])
//        }
//    }
//    repositories {
//        maven {
//            setUrl("https://maven.pkg.jetbrains.space/vadymhrynyk/p/main/cinematica")
//            credentials {
//                username = project.findProperty("spaceUsername") as String
//                password = project.findProperty("spacePassword") as String
//            }
//        }
//    }
//}