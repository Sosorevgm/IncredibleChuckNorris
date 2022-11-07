plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.compile_sdk)
    buildToolsVersion(Config.build_tools_version)

    defaultConfig {
        minSdkVersion(Config.min_sdk)
        targetSdkVersion(Config.target_sdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Module.ui))

    implementation(Kotlin.core)
    implementation(Kotlin.std_lib)
    implementation(Design.appcompat)
    implementation(Design.material)
    implementation(Design.constraint_layout)

    implementation(Dagger.core)
    implementation(Dagger.android)
    implementation(Dagger.android_support)
    kapt(Dagger.processor)
    kapt(Dagger.compiler)

    implementation(Navigation.cicerone)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.test_ext_JUnit)
    androidTestImplementation(TestImpl.espresso_core)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
