package com.sweta.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import com.sweta.data.BuildConfig

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("appid","${BuildConfig.version_code}")
                .addHeader("device_platform", "android")
                .build()
        )
    }
}