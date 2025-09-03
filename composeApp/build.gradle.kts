import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "1.9.20"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Ktor
            implementation("io.ktor:ktor-client-core:2.3.5")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
            implementation("io.ktor:ktor-client-logging:2.3.5")

            // Serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

            // Koin
            implementation("io.insert-koin:koin-core:3.5.0")
            implementation("io.insert-koin:koin-compose:1.1.0")
            implementation("io.insert-koin:koin-androidx-compose:3.5.0")

            // Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        }

        androidMain.dependencies {
            // Ktor Android Engine
            implementation("io.ktor:ktor-client-android:2.3.5")
            implementation("io.insert-koin:koin-android:3.5.0")
        }

        iosMain.dependencies {
            // Ktor iOS Engine
            implementation("io.ktor:ktor-client-darwin:2.3.5")
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.aliexpressshoppingbd.ali2bd"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.aliexpressshoppingbd.ali2bd"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
