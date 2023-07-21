package com.movies.data.remote.service.result_handler.resource

sealed class Resource<Any>(val loader: Boolean) {
    data class Success<Any>(val data: Any) : Resource<Any>(false)
    data class Error<T>(val errorMessage: Throwable) : Resource<T>(false)
    class Loader<T : Any>() : Resource<T>(true)
}