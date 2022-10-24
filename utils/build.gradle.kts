plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.voyager.utils"
    compileSdk = Versions.sdkCompile

    defaultConfig {
        minSdk = Versions.sdkMin
        targetSdk = Versions.sdkTarget
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
    api("com.opencsv:opencsv:5.7.0")
}

