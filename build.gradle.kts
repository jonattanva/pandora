buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.10")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.8.1")
        classpath("com.google.firebase:perf-plugin:1.4.1")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}