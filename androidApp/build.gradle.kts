plugins {
    id("com.android.application")
    kotlin("android")
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

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")
    androidTestImplementation("androidx.navigation:navigation-testing:2.4.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.1")
}