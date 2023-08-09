package com.movies.data.remote.network

class NetworkBuilder<T> {
    var execute: (suspend () -> T)? = null
    var success: ( (T) -> Unit)? = null
    var error: ( (Throwable) -> Unit)? = null
    var loading: ( (Boolean) -> Unit)? = null

    fun<T> NetworkBuilder<T>.executeApi(execute: (suspend () -> T)){
        this.execute = execute
    }

    fun<T> NetworkBuilder<T>.success(success: ( (T) -> Unit)){
        this.success = success
    }

    fun<T> NetworkBuilder<T>.error(error: ( (Throwable) -> Unit)){
        this.error = error
    }

    fun<T> NetworkBuilder<T>.loading(loading: ( (Boolean) -> Unit)){
        this.loading = loading
    }
}