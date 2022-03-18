package com.sweta.domain.utils

import java.lang.Exception

sealed class ResultWrapper<out T: Any> {
    class Success<out T:Any>(val data: T?): ResultWrapper<T>()
    class PagedSuccess<out T:Any>(val data:T, val isLastPage:Boolean) : ResultWrapper<T>()
    data class GenericError( val exception: Exception? = null): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
    object AuthError: ResultWrapper<Nothing>()
}