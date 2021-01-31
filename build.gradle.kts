buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:4.1.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21-2")

        classpath ("com.google.gms:google-services:4.3.5")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.4.1")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}