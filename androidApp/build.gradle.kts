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
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.animation:animation:1.1.1")
    implementation("androidx.navigation:navigation-compose:2.4.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("androidx.compose.ui:ui-tooling-preview:1.1.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.1.1")

    implementation(platform("com.google.firebase:firebase-bom:29.3.1"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")

    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-android-compiler:2.41")

    implementation("io.coil-kt:coil:2.0.0-rc03")
    implementation("io.coil-kt:coil-compose:2.0.0-rc03")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:core:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.3")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")
    androidTestImplementation("com.google.firebase:testlab-instr-lib:0.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.4.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.1")
}