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

    api("androidx.core:core-ktx:1.9.0")
    api("androidx.appcompat:appcompat:1.5.1")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    api("androidx.navigation:navigation-fragment-ktx:2.5.2")
    api("androidx.navigation:navigation-ui-ktx:2.5.2")
    api("androidx.annotation:annotation:1.5.0")

    testApi("junit:junit:4.13.2")
    testApi("androidx.test:core:1.4.0")
    testApi("org.mockito:mockito-core:4.8.1")
    testApi("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testApi("io.mockk:mockk:1.13.2")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    testApi("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}")
    implementation("androidx.work:work-runtime-ktx:${Versions.workManager}")
    androidTestImplementation("androidx.work:work-testing:${Versions.workManager}")

    api("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    api("androidx.hilt:hilt-work:1.0.0")
    implementation("com.google.android.gms:play-services-base:${Versions.playservicesBase}")

    testImplementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("io.ktor:ktor-client-core:2.1.2")
    implementation("io.ktor:ktor-client-okhttp:2.1.2")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")


    api("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    // convertors
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    api("com.google.code.gson:gson:2.9.1")
    api("com.squareup.retrofit2:converter-gson:2.9.0")

    // mock tests
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")
    api("io.coil-kt:coil-compose:2.2.2")

    api("androidx.navigation:navigation-compose:2.5.3")
}

kapt {
    correctErrorTypes = true
}
