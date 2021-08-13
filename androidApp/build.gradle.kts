plugins {
    id("com.android.application")
    id("com.squareup.sqldelight")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.monolieta.pandora.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

sqldelight {
    database("Pandora") {
        packageName = "com.monolieta"
    }
}