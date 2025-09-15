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
            implementation(libs.coil3.network)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.materialIconsExtended)

            // Serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

            // Koin - multiplatform compatible
            implementation("io.insert-koin:koin-core:3.5.0")
            implementation("io.insert-koin:koin-compose:1.1.0")

            // Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("media.kamel:kamel-image-default:1.0.7")

            implementation(libs.coil3)
          //  implementation(libs.coil3.network)
            implementation(libs.compose.navigation)

            // Ktor dependencies from version catalog
            implementation(libs.ktor.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.logging) // Adding ktor-logging dependency
        }

        androidMain.dependencies {
            // Ktor Android Engine using version from catalog
            implementation(libs.ktor.okhttp)
            implementation("io.insert-koin:koin-android:3.5.0")
            // Android-specific Koin Compose integration
            implementation("io.insert-koin:koin-androidx-compose:3.5.0")
        }

        iosMain.dependencies {
            // Ktor iOS Engine using version from catalog
            implementation(libs.ktor.darwin.ios)
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
        signingConfigs {
            create("release") {
                val storeFilePath = project.findProperty("RELEASE_STORE_FILE") as String
                val storePasswordProp = project.findProperty("RELEASE_STORE_PASSWORD") as String
                val keyAliasProp = project.findProperty("RELEASE_KEY_ALIAS") as String
                val keyPasswordProp = project.findProperty("RELEASE_KEY_PASSWORD") as String


                    storeFile = file(storeFilePath)
                    storePassword = storePasswordProp
                    keyAlias = keyAliasProp
                    keyPassword = keyPasswordProp

            }
        }
        getByName("release") {
            // Enables code shrinking, obfuscation, and optimization with R8
            isMinifyEnabled = true

            // Enables resource shrinking (removes unused resources)
            isShrinkResources = true

            // Uses the default ProGuard / R8 rules file
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // Optional: Sign your release build (if you have a keystore)
            signingConfig = signingConfigs.getByName("release")
        }

        getByName("debug") {
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
