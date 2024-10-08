package com.rer.buildsrc

object Deps {
    private const val composeVersion = "1.5.0-rc01"
    private const val material3Version = "1.1.1"
    private const val jUnitVersion = "4.13.2"
    private const val testRunnerVersion = "1.5.1"
    private const val rulesVersion = "1.4.0"
    private const val lifecycleVersion = "2.5.1"
    private const val jupiterVersion = "5.8.1"
    private const val coilVersion = "2.5.0"
    const val kotlinCompilerExtensionVersion = "1.5.3"
    private const val accompanistVersion = "0.25.1"
    private const val googleTruthVersion = "1.4.2"
    private const val kotlinxSerializationVersion = "1.6.3"
    private const val hiltVersion = "2.51.1"
    private const val retrofitVersion = "2.9.0"


    //Core
    const val coreKtx = "androidx.core:core-ktx:1.8.0"
    const val activityCompose = "androidx.activity:activity-compose:1.5.1"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:$lifecycleVersion"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val workRuntime = "androidx.work:work-runtime-ktx:2.7.1"
    const val kotlinxSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion"

    //UI
    const val composeRuntime = "androidx.compose.runtime:runtime-livedata:$composeVersion"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    const val composeIconsExtended =
        "androidx.compose.material:material-icons-extended:$composeVersion"
    const val composeUi = "androidx.compose.ui:ui:$composeVersion"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val composeUigraphics = "androidx.compose.ui:ui-graphics:$composeVersion"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val composeMaterial3 = "androidx.compose.material3:material3:$material3Version"
    const val composeMaterial3WindowSize =
        "androidx.compose.material3:material3-window-size-class:$material3Version"
    const val composeReimagined = "dev.olshevski.navigation:reimagined-material3:1.5.0"
    const val lottie = "com.airbnb.android:lottie-compose:5.2.0"

    //test
    const val jUnit = "junit:junit:$jUnitVersion"
    const val jupiter = "org.junit.jupiter:junit-jupiter:$jupiterVersion"
    const val composeTesting = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"
    const val googleTruth = "com.google.truth:truth:$googleTruthVersion"
    const val rules = "androidx.test:rules:$rulesVersion"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0"
    const val mockk = "io.mockk:mockk:1.12.4"
    const val jUnit5 = "org.junit.jupiter:junit-jupiter-api:5.10.0"
    const val jUnit5Engine = "org.junit.jupiter:junit-jupiter-engine:5.10.0"
    const val jUnit5Vintage = "org.junit.vintage:junit-vintage-engine:5.10.0"
    const val cashAppTurbine = "app.cash.turbine:turbine:1.1.0"

    //acommpaist
    const val accompanistPager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
    const val accompanistPagerIndicator =
        "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion"
    const val accompanistSystemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion"
    const val accompanistDrawablePainter =
        "com.google.accompanist:accompanist-drawablepainter:$accompanistVersion"
    const val accompanistFlowLayout =
        "com.google.accompanist:accompanist-flowlayout:$accompanistVersion"
    const val accompanistPermission =
        "com.google.accompanist:accompanist-permissions:$accompanistVersion"
    const val accompanistWebView = "com.google.accompanist:accompanist-webview:$accompanistVersion"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion"

    //Coroutine
    val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core: "
    val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"

    //Hilt
    val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    val hiltNavCompose = "androidx.hilt:hilt-navigation-compose:1.2.0"

    //Coil
    const val coil = "io.coil-kt:coil-compose:$coilVersion"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val logingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.1"
    const val converterRetrofit = "com.squareup.retrofit2:converter-gson:$retrofitVersion"

}