plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha06")
    implementation("androidx.compose.ui:ui:1.0.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.material:material:1.0.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.1")
    implementation("androidx.compose.material:material-icons-extended:1.0.1")

    implementation("io.coil-kt:coil:1.3.2")
    implementation("io.coil-kt:coil-compose:1.3.2")

    implementation(platform("com.google.firebase:firebase-bom:28.3.1"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    debugImplementation("androidx.compose.ui:ui-tooling:1.0.1")
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

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.1"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}