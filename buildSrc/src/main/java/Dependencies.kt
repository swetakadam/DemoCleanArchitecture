/**
 * To define plugins
 */
//object BuildPlugins {
//    val android =  "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
//    val kotlin =  "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
//}

/**
 * To define dependencies
 */
object Deps {
    const val appCompat =  "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val timber =  "com.jakewharton.timber:timber:${Versions.timber}"
    const val kotlin =  "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val materialDesign =  "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit =  "junit:junit:${Versions.jUnit}"
    const val androidXCore =  "androidx.core:core-ktx:${Versions.androidXCore}"
    const val composeUi =  "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val composeMaterial =  "androidx.compose.material:material:${Versions.composeVersion}"
    const val composeUiTooling =  "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val lifecycle =  "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val activityCompose =  "androidx.activity:activity-compose:${Versions.activityCompose}"




    const val mockito = "org.mockito:mockito-core:${Versions.mockito_version}"
    const val coroutine_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val androidx_core_test = "androidx.arch.core:core-testing:${Versions.androidx_core_test}"
    const val fragment_test = "androidx.fragment:fragment-testing:${Versions.fragment_version}"
    const val dex_maker_mockito = "com.linkedin.dexmaker:dexmaker-mockito:${Versions.dex_maker_version}"
    const val espresso_idling = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso_core}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.junit_ext}"


    const val testImplementation =  "junit:junit:${Versions.jUnit}"
    const val androidTestImplementation =  "androidx.test.ext:junit:${Versions.androidJunit}"
    const val androidTestExpressoImplementation =  "androidx.test.espresso:espresso-core:${Versions.androidExpresso}"
    const val androidTestImplementationComposeUi =  "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
    const val debugImplementationComposeTooling =  "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"


    const val kotlin_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit_coroutine_adapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit_adapter}"
    const val okhttp_logger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logger}"
    const val square_okhttp_bom = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp_bom}"
    const val square_okhttp3 = "com.squareup.okhttp3:okhttp"
    const val square_okhttp_logging = "com.squareup.okhttp3:logging-interceptor"


    const val koin_android = "io.insert-koin:koin-android:${Versions.koin_version}"
    const val koin_androidx_scope = "io.insert-koin:koin-androidx-scope:${Versions.koin_version}"
    const val koin_viewmodel = "io.insert-koin:koin-androidx-viewmodel:${Versions.koin_version}"

    //Coil (Image Uploading)
    const val accompanist = "com.google.accompanist:accompanist-coil:${Versions.coil_version}"

    //Paging 3.0
    const val paging_compose = "androidx.paging:paging-compose:${Versions.paging_compose_version}"


    const val viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val viewmodel_saved_state =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle_version}"

    const val koin = "io.insert-koin:koin-gradle-plugin:${Versions.koin_version}"
    const val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.lifecycle_ext}"




}