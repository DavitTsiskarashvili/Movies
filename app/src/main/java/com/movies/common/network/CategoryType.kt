package com.movies.common.network

import com.movies.data.remote.service.api.ServiceApi

enum class CategoryType (val value: String){
    POPULAR(ServiceApi.POPULAR),
    UPCOMING(ServiceApi.UPCOMING),
    TOP_RATED(ServiceApi.TOP_RATED),
    NOW_PLAYING(ServiceApi.NOW_PLAYING)
}