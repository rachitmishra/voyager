plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.voyager.design"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.voyager.design"
        minSdk = 24
        targetSdk = 33
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

    androidTestImplementation("androidx.test.ext:junit:")
    androidTestApi("androidx.test.espresso:espresso-core:3.4.0")
    debugApi("androidx.compose.ui:ui:${Versions.compose}")
    debugApi("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    api("com.google.android.material:material:${Versions.material}")
    debugApi("androidx.compose.material3:material3:${Versions.composeMaterial}")
    androidTestApi("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
    debugApi("androidx.compose.ui:ui-tooling:${Versions.compose}")
    debugApi("androidx.compose.ui:ui-test-manifest:${Versions.compose}")
    api("androidx.activity:activity-compose:${Versions.composeActivity}")
    api("androidx.constraintlayout:constraintlayout:${Versions.contraintLayout}")

}
