package com.sweta.data.network

import com.google.gson.Gson
import com.sweta.domain.utils.ResultWrapper
import retrofit2.Response

fun <T:Any,R:Any> Response<T>.getErrorResultWrapper() : ResultWrapper<R> {

    return when(this.code()) {
        401 -> {
            ResultWrapper.AuthError
        }
        500 -> {
            ResultWrapper.GenericError(Exception("Internal Server Error"))
        }

        in 400..499 -> {
            try {
                var errorResponse: ErrorResponse? = Gson().fromJson(this.errorBody()?.charStream(), ErrorResponse::class.java)
                ResultWrapper.GenericError( Exception(errorResponse?.message ?: (errorResponse?.error ?: "Bad Request")))
            }catch (ex: Exception){
                ResultWrapper.GenericError( Exception("Bad request"))
            }
        }

        else -> {
            ResultWrapper.NetworkError
        }
    }
}