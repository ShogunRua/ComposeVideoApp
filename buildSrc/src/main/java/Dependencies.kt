@file:OptIn(ExperimentalStdlibApi::class)

import Versions.appcompatVer
import Versions.composeNav
import Versions.composeVer
import Versions.coroutinesVer
import Versions.daggerHiltVer
import Versions.ktorVer
import Versions.roomVersion

object Versions {
    const val appcompatVer = "1.5.1"
    const val composeVer = "1.3.0-alpha02"
    const val daggerHiltVer = "2.44.2"
    const val ktorVer = "2.0.2"
    const val coroutinesVer = "1.5.2"
    const val roomVersion = "2.4.3"
    const val composeNav = "2.4.0-beta02"
}

object Dependencies {

    //Android
    private const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
    private const val activityCompose = "androidx.activity:activity-compose:1.6.0"
    private const val appcompat = "androidx.appcompat:appcompat:$appcompatVer"
    private const val appcompatResources = "androidx.appcompat:appcompat-resources:$appcompatVer"

    //Compose
    private const val composeUi = "androidx.compose.ui:ui:$composeVer"
    private const val material = "androidx.compose.material:material:$composeVer"
    private const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVer"
    private const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVer"
    private const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVer"
    private const val accompanistInsets = "com.google.accompanist:accompanist-insets:0.24.11-rc"
    private const val accompanistSystemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:0.24.11-rc"
    private const val composeFoundation =
        "androidx.compose.foundation:foundation-layout:$composeVer"
    private const val composeAnimation = "androidx.compose.animation:animation:$composeVer"
    private const val composeViewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
    private const val composeConstraintlayout =
        "androidx.constraintlayout:constraintlayout-compose:1.0.1"

    //Arrow
    const val arrowStack = "io.arrow-kt:arrow-stack:1.0.1"
    private const val arrowCore = "io.arrow-kt:arrow-core"
    private const val arrowFxStm = "io.arrow-kt:arrow-fx-stm"

    //Dagger
    private const val daggerHilt = "com.google.dagger:hilt-android:$daggerHiltVer"
    private const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:$daggerHiltVer"

    //Ktor
    private const val ktorClientCore = "io.ktor:ktor-client-core:$ktorVer"
    private const val ktorClientAndroid = "io.ktor:ktor-client-android:$ktorVer"
    private const val ktorNegotiation = "io.ktor:ktor-client-content-negotiation:$ktorVer"
    private const val ktorJson = "io.ktor:ktor-serialization-kotlinx-json:$ktorVer"
    private const val ktorClientCio = "io.ktor:ktor-client-cio:$ktorVer"
    private const val ktorClientLogging = "io.ktor:ktor-client-logging:$ktorVer"
    private const val ktorClientResources = "io.ktor:ktor-client-resources:$ktorVer"
    private const val ktorClientOkhttp = "io.ktor:ktor-client-okhttp:$ktorVer"

    //Coroutines
    private const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVer"
    private const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVer"

    //Ktx
    private const val coreKtx = "androidx.core:core-ktx:1.8.0"
    private const val core = "androidx.core:core:1.8.0"
    private const val activityKtx = "androidx.activity:activity-ktx:1.6.0"
    private const val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.3"

    //Test
    private const val junitExt = "androidx.test.ext:junit:1.1.3"
    private const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    private const val junit4 = "androidx.compose.ui:ui-test-junit4:$composeVer"
    private const val junit = "junit:junit:4.13.2"

    //Navigation
    private const val navigationCompose = "androidx.navigation:navigation-compose:$composeNav"

    //Viewmodel
    private const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"

    //Room
    private const val room = "androidx.room:room-runtime:$roomVersion"
    private const val roomKapt = "androidx.room:room-compiler:$roomVersion"
    private const val roomCoroutines = "androidx.room:room-ktx:$roomVersion"

    val appLibraries = buildList {
        add(lifecycleRuntime)

        add(appcompat)
        add(appcompatResources)

        add(activityCompose)
        add(composeUi)
        add(material)
        add(uiToolingPreview)
        add(uiTooling)
        add(uiTestManifest)
        add(accompanistInsets)
        add(accompanistSystemUiController)
        add(composeFoundation)
        add(composeAnimation)
        add(composeViewmodel)
        add(composeConstraintlayout)

        add(arrowCore)
        add(arrowFxStm)

        add(daggerHilt)

        add(ktorClientCore)
        add(ktorClientAndroid)
        add(ktorNegotiation)
        add(ktorJson)
        add(ktorClientCio)
        add(ktorClientLogging)
        add(ktorClientResources)
        add(ktorClientOkhttp)

        add(coroutinesCore)
        add(coroutinesAndroid)

        add(core)
        add(coreKtx)
        add(activityKtx)
        add(fragmentKtx)

        add(navigationCompose)

        add(viewmodel)

        add(room)
        add(roomCoroutines)

    }

    val androidTestLibraries = buildList {
        add(junitExt)
        add(espresso)
        add(junit4)
    }

    val testLibraries = buildList {
        add(junit)
    }

    val kaptCompilers = buildList {
        add(daggerHiltCompiler)
        add(roomKapt)
    }
}
