package com.movies.data.remote.service.result_handler.retrofit

import android.util.Log
import com.movies.data.remote.service.result_handler.resource.Resource
import retrofit2.Response

inline fun <DTO : Any> apiDataFetcher(
    apiResponse: () -> Response<DTO>
): Resource<DTO> {
    return try {
        val response = apiResponse.invoke()
        Log.d("list123", "${response}")

        if (response.isSuccessful) {
            Resource.Success(response.body()!!)
        } else {
            Resource.Error(Throwable(response.message()))
        }
    } catch (e: Exception) {
        Resource.Error(errorMessage = e)
    }
}