plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

val composerVersion = "1.0.1"

dependencies {
    implementation(project(":shared"))
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:$composerVersion")
    implementation("androidx.compose.animation:animation:$composerVersion")
    implementation("androidx.compose.material:material:$composerVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composerVersion")
    implementation("androidx.compose.ui:ui-tooling:1.1.0-alpha02")
    implementation("androidx.compose.material:material-icons-extended:$composerVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composerVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0-alpha03")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha07")
    implementation("androidx.palette:palette:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    implementation("com.google.android.material:material:1.4.0")

    implementation("io.coil-kt:coil:1.3.2")
    implementation("io.coil-kt:coil-compose:1.3.2")

    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    implementation(platform("com.google.firebase:firebase-bom:28.3.1"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    debugImplementation("androidx.compose.ui:ui-tooling:$composerVersion")
}

kapt {
    correctErrorTypes = true
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.monolieta.pandora.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composerVersion
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}