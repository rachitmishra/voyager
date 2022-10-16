import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.voyager.core"
    compileSdk = Versions.sdkCompile

    defaultConfig {
        minSdk = Versions.sdkMin
        targetSdk= Versions.sdkTarget

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

    api("androidx.core:core-ktx:1.9.0")
    api("androidx.appcompat:appcompat:1.5.1")
    testApi("junit:junit:4.13.2")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    api("androidx.navigation:navigation-fragment-ktx:2.5.2")
    api("androidx.navigation:navigation-ui-ktx:2.5.2")
    api("androidx.annotation:annotation:1.5.0")
}

kapt {
    correctErrorTypes = true
}
