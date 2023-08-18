package com.movies.data.remote.service.result_handler.retrofit

import retrofit2.Response

inline fun <DTO : Any> apiDataFetcher(
    apiResponse: () -> Response<DTO>
): DTO {
    val response = apiResponse.invoke()
    if (response.isSuccessful) {
        return response.body()!!
    } else {
        throw IllegalArgumentException(response.message())
    }
}