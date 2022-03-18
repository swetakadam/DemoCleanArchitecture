package com.sweta.data.network.interceptors

import com.sweta.data.network.ConnectionManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkStatusInterceptor  constructor(private val connectionManager: ConnectionManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailableException()
        }
    }
}
class NetworkException(message: String): Exception(message)
class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)