import Deps.testImplementation

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "BASE_API_URL", "\"https://picsum.photos/\"")
        buildConfigField("long", "version_code", "${Versions.version_code}")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(project(":domain"))
    implementation(Deps.androidXCore)
    implementation(Deps.kotlin)
    implementation ("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")



    implementation(Deps.gson)
    implementation(Deps.retrofit)
    implementation(Deps.retrofit_converter)
    // define any required OkHttp artifacts without version
    implementation(Deps.square_okhttp3)
    implementation(Deps.square_okhttp_logging)
    implementation(platform(Deps.square_okhttp_bom))

    implementation(Deps.retrofit_coroutine_adapter)
    //Room
    //kapt Deps.room_compiler
    //implementation Deps.room_ktx
    implementation(Deps.kotlin_coroutines)

    // Koin main features for Android
    implementation ("io.insert-koin:koin-android:3.1.5")
// No more koin-android-viewmodel, koin-android-scope, koin-android-fragment

// Java Compatibility
    implementation ("io.insert-koin:koin-android-compat:3.1.5")
// Jetpack WorkManager
    implementation ("io.insert-koin:koin-androidx-workmanager:3.1.5")
// Navigation Graph
    implementation ("io.insert-koin:koin-androidx-navigation:3.1.5")
// Jetpack Compose
    implementation ("io.insert-koin:koin-androidx-compose:3.1.5")

//    implementation(Deps.koin_android)
//    implementation(Deps.koin_androidx_scope)
//    implementation(Deps.koin_viewmodel)
    implementation(Deps.timber)

    //Coil (Image Uploading)
    implementation(Deps.accompanist)
    //Paging 3.0
    implementation(Deps.paging_compose)

    testImplementation(Deps.junit)
    testImplementation(Deps.mockito)
    testImplementation(Deps.coroutine_test)
    testImplementation ("org.mockito:mockito-inline:3.11.2")


    androidTestImplementation(Deps.junit_ext)
    androidTestImplementation(Deps.espresso_core)
    androidTestImplementation(Deps.androidx_core_test)
}