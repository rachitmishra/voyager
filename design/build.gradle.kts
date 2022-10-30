plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.voyager.design"
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
    api("androidx.compose.ui:ui-text-google-fonts:1.3.0")

}
