buildscript {
    dependencies {
        repositories {
            google()
            mavenCentral()

        }
        classpath("com.google.gms:google-services:4.4.1")
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("jvm") version "1.9.0"
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("com.android.library") version "7.0.3" apply  false
    id("org.jetbrains.kotlin.kapt") version "1.9.0"
    id("com.google.dagger.hilt.android") version "2.49" apply false
}