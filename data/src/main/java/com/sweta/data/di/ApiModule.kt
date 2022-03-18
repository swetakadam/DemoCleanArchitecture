package com.sweta.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.sweta.data.BuildConfig
import com.sweta.data.GalleryApi
import com.sweta.data.network.ConnectionManager
import com.sweta.data.network.interceptors.HeaderInterceptor
import com.sweta.data.network.interceptors.NetworkStatusInterceptor
import com.sweta.data.network.interceptors.SimpleAuthInterceptor
import com.sweta.data.network.mappers.ApiMapper
import com.sweta.data.network.models.ApiGalleryItem
import com.sweta.domain.models.GalleryImage

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single { ConnectionManager(androidContext()) }
    single { NetworkStatusInterceptor(get()) }
    single { SimpleAuthInterceptor(get()) }
    single { HeaderInterceptor() }
    single { provideOkHttpLoggingInterceptor() }
    single { provideOkHttpClient(get(), get(), get(), get()) }
    single { provideRetrofit(get()) }
    single { provideGalleryApi(get()) }
    single { provideSharedPrefs(androidApplication())}

    //mapper objects
    single(named("ApiGalleryItemMapper")){com.sweta.data.network.mappers.ApiGalleryItemMapper() as ApiMapper<ApiGalleryItem?,GalleryImage> }

}

fun provideSharedPrefs(androidApplication: Application):SharedPreferences {
    return androidApplication.getSharedPreferences(
        "demo_shared_prefs",
        Context.MODE_PRIVATE
    )
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(
    authInterceptor:SimpleAuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    networkStatusInterceptor: NetworkStatusInterceptor,
    headerInterceptor: HeaderInterceptor
): OkHttpClient {
    val client = OkHttpClient().newBuilder()

    client.addInterceptor(networkStatusInterceptor)
    client.addInterceptor(authInterceptor)
    client.addInterceptor(headerInterceptor)
    client.addInterceptor(loggingInterceptor)

    //client.addNetworkInterceptor(StethoInterceptor())


    return client.build()
}

fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}

fun provideGalleryApi(retrofit: Retrofit): GalleryApi = retrofit.create(GalleryApi::class.java)
