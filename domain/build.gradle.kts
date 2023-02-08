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

    implementation(Deps.kotlin)
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")




    implementation(Deps.paging_compose)
    //implementation(Deps.lifecycle)
    //implementation(Deps.lifecycle_extensions)
    implementation(Deps.livedata_ktx)
    implementation(Deps.androidXCore)

    testImplementation(Deps.mockito)
    testImplementation(Deps.junit)
    testImplementation(Deps.junit_ext)
    testImplementation(Deps.coroutine_test)
}