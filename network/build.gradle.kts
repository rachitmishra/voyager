plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization") version Versions.kotlin
}

android {
    namespace = "com.voyager.network"
    compileSdk = 33

    defaultConfig {
        minSdk =  24
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    testImplementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("io.ktor:ktor-client-core:2.1.2")
    implementation("io.ktor:ktor-client-okhttp:2.1.2")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    api("com.google.code.gson:gson:2.9.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")
}
