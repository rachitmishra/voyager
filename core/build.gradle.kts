import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.voyager.core"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.voyager.core"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api("androidx.core:core-ktx:1.7.0")
    api("androidx.appcompat:appcompat:1.5.1")
    testApi("junit:junit:4.13.2")
    api("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    api("androidx.navigation:navigation-fragment-ktx:2.4.1")
    api("androidx.navigation:navigation-ui-ktx:2.4.1")
    api("androidx.annotation:annotation:1.5.0")
}

kapt {
    correctErrorTypes = true
}
