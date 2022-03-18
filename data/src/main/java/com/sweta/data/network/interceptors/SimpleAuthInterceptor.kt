package com.sweta.data.network.interceptors

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.android.BuildConfig

const val SECURED_PREF_KEY_TOKEN = "token"

class SimpleAuthInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {

    var token: String = ""
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        token = sharedPreferences.getString(SECURED_PREF_KEY_TOKEN, "") ?: ""

        val originalHttpUrl = original.url
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", "Bearer: $token")
            .url(originalHttpUrl)


        val request = requestBuilder.build()
        return chain.proceed(request)

    }

}
