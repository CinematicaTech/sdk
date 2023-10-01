plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
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