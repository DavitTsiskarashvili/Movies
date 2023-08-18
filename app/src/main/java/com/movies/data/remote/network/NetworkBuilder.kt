package com.movies.data.remote.network

class NetworkBuilder<T> {
    var execute: (suspend () -> T)? = null
    var success: (suspend (T) -> Unit)? = null
    var error: (suspend (Throwable) -> Unit)? = null
    var loading: (suspend (Boolean) -> Unit)? = null

    fun<T> NetworkBuilder<T>.executeApi(execute: (suspend () -> T)){
        this.execute = execute
    }

    fun<T> NetworkBuilder<T>.success(success: (suspend (T) -> Unit)){
        this.success = success
    }

    fun<T> NetworkBuilder<T>.error(error: (suspend (Throwable) -> Unit)){
        this.error = error
    }

    fun<T> NetworkBuilder<T>.loading(loading: (suspend (Boolean) -> Unit)){
        this.loading = loading
    }
}