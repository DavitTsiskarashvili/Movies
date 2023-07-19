package com.movies.common.network

import com.movies.data.remote.service.api.ServiceApi

enum class Category (val value: String){
    POPULAR(ServiceApi.POPULAR), TOP_RATED(ServiceApi.TOP_RATED)
}