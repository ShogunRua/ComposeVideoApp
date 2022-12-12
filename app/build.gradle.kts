import Dependencies.androidTestLibraries
import Dependencies.appLibraries
import Dependencies.arrowStack
import Dependencies.kaptCompilers
import Dependencies.testLibraries

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.7.10"
    id("kotlin-parcelize")
}

android {
    compileSdk = 33
    namespace = "com.shogunrua.composevideoapp"

    defaultConfig {
        applicationId = "com.shogunrua.composevideoapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0-rc01"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(appLibraries)
    androidTestImplementation(androidTestLibraries)
    testImplementation(testLibraries)
    kapt(kaptCompilers)

    //Arrow stack
    implementation(platform(arrowStack))
}
