object Versions {
    const val core = "1.3.0"
    const val std_lib = "1.3.72"

    const val appcompat = "1.1.0"
    const val constraint_layout = "1.1.3"
    const val swipe_to_refresh = "1.1.0"
    const val view_pager_2 = "1.0.0"
    const val material = "1.2.1"

    const val cicerone = "5.0.0"

    const val coroutines_core = "1.3.7"
    const val coroutines_android = "1.3.4"

    const val retrofit = "2.9.0"
    const val retrofit_gson_converter = "2.6.0"
    const val retrofit_coroutines_adapter = "0.9.2"

    const val logging_interceptor = "4.9.0"

    const val dagger = "2.31.2"

    const val room_version = "2.2.5"

    const val glide = "4.11.0"
    const val glide_compiler = "4.11.0"

    const val timber = "4.7.1"

    const val shimmer = "0.5.0"

    const val profanity_filter = "1.0.0"

    const val firebase_bom = "26.5.0"

    const val canary = "2.6"

    const val jUnit = "4.12"
    const val text_ext_JUnit = "1.1.1"
    const val espresso_core = "3.2.0"
}

object Config {
    const val application_id = "com.incredible.chuck.norris"
    const val compile_sdk = 30
    const val min_sdk = 24
    const val target_sdk = 30
    const val build_tools_version = "30.0.0"
}

object Releases {
    const val version_code = 10
    const val version_name = "1.8"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val std_lib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.std_lib}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val swipe_to_refresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_to_refresh}"
    const val view_pager_2 = "androidx.viewpager2:viewpager2:${Versions.view_pager_2}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Navigation {
    const val cicerone = "ru.terrakok.cicerone:cicerone:${Versions.cicerone}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    const val android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson_converter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit_gson_converter}"
    const val coroutines_adapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit_coroutines_adapter}"
}

object Okhttp {
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
}

object Dagger {
    const val core = "com.google.dagger:dagger:${Versions.dagger}"
    const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val ktx = "androidx.room:room-ktx:${Versions.room_version}"
    const val kapt = "androidx.room:room-compiler:${Versions.room_version}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler}"
}

object Timber {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Shimmer {
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
}

object Filter {
    const val profanity_filter =
        "com.sosorevgm.profanityfilter:profanityfilter:${Versions.profanity_filter}"
}

object Firebase {
    const val bom = "com.google.firebase:firebase-bom:${Versions.firebase_bom}"
    const val analytics_ktx =
        "com.google.firebase:firebase-analytics-ktx"
    const val crashlytics_ktx =
        "com.google.firebase:firebase-crashlytics-ktx"
    const val performance_ktx =
        "com.google.firebase:firebase-perf-ktx"
}

object Leak {
    const val canary = "com.squareup.leakcanary:leakcanary-android:${Versions.canary}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val test_ext_JUnit = "androidx.test.ext:junit:${Versions.text_ext_JUnit}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
}