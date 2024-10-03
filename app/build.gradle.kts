import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.testtaskfoodapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testtaskfoodapp"
        minSdk = 28
        targetSdk = 34
        versionCode = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH")).toInt()

        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        debug {
            multiDexEnabled = true
            isMinifyEnabled = false
            isCrunchPngs = false
            isDebuggable = true

            buildConfigField("String", "BASE_URL", "\"https://test-task-server.mediolanum.f17y.com\"")
        }

        release {
            multiDexEnabled = true
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = false
            isCrunchPngs = true

            buildConfigField("String", "BASE_URL", "\"https://test-task-server.mediolanum.f17y.com\"")

            proguardFiles( getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildFeatures {
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("io.coil-kt:coil-compose:2.4.0")

// Module
    implementation(project(":data"))
    implementation(project(":domain"))

//Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

// Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation("androidx.lifecycle:lifecycle-process:2.8.6")
    implementation("androidx.wear.compose:compose-material:1.4.0")
    implementation("com.google.android.gms:play-services-vision-common:19.1.3")
    implementation("androidx.media3:media3-effect:1.4.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.compose.ui:ui-graphics:1.7.2")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")

// Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

// Lifecycle components
    implementation (libs.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.common.java8)

// Compose
    implementation (libs.lottie.compose)
    implementation (libs.material3)
    implementation (libs.androidx.runtime.livedata)

}

kapt {
    correctErrorTypes = true
}