plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(Config.compile_sdk)
    buildToolsVersion(Config.build_tools_version)

    defaultConfig {
        applicationId = Config.application_id
        minSdkVersion(Config.min_sdk)
        targetSdkVersion(Config.target_sdk)
        versionCode = Releases.version_code
        versionName = Releases.version_name

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
    implementation(Kotlin.core)
    implementation(Kotlin.std_lib)

    implementation(Design.appcompat)
    implementation(Design.constraint_layout)
    implementation(Design.swipe_to_refresh)
    implementation(Design.view_pager_2)
    implementation(Design.material)

    implementation(Navigation.cicerone)

    implementation(Coroutines.core)
    implementation(Coroutines.android)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson_converter)
    implementation(Retrofit.coroutines_adapter)
    implementation(Okhttp.logging_interceptor)

    implementation(Koin.android)
    implementation(Koin.view_model)
    implementation(Koin.fragment)
    implementation(Koin.ext)

    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.kapt)

    implementation(Glide.glide)
    annotationProcessor(Glide.glide_compiler)

    implementation(Timber.timber)

    implementation(Shimmer.shimmer)

    implementation(Filter.profanity_filter)

    implementation(Firebase.analytics)
    implementation(Firebase.crashlytics)
    implementation(Firebase.analytics_kotlin)
    implementation(Firebase.crashlytics_kotlin)

    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.test_ext_JUnit)
    androidTestImplementation(TestImpl.espresso_core)
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
