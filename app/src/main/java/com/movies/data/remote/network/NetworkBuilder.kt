package com.movies.data.remote.network

class NetworkBuilder<T> {

    var execute: (suspend () -> T)? = null
    var success: ((T) -> Unit)? = null
    var error: (() -> Unit)? = null
    var loading: (() -> Unit)? = null

}