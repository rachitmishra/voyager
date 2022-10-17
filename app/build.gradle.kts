plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.voyager"
    compileSdk = Versions.sdkCompile

    defaultConfig {
        applicationId = "com.voyager"
        minSdk = Versions.sdkMin
        targetSdk= Versions.sdkTarget
        versionCode = 1
        versionName= "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-di"))
    implementation(project(":design"))
    implementation(project(":weather"))
    implementation(project(":location"))
    implementation(project(":core-async"))
    implementation(project(":permissions"))
    implementation(project(":network"))
    implementation(project(":location"))
}
