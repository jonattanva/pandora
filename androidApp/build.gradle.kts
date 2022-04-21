plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.monolieta.pandora.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    implementation("androidx.palette:palette:1.0.0")
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.animation:animation:1.1.1")
    implementation("androidx.navigation:navigation-compose:2.4.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation(platform("com.google.firebase:firebase-bom:29.3.1"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")

    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-android-compiler:2.41")

    implementation("io.coil-kt:coil:2.0.0-rc03")
    implementation("io.coil-kt:coil-compose:2.0.0-rc03")

    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")
    androidTestImplementation("androidx.navigation:navigation-testing:2.4.2")
}