package com.movies.data.remote.network

class NetworkBuilder<T> {
    var execute: (suspend () -> T)? = null
    var success: ((T) -> Unit)? = null
    var error: (() -> Unit)? = null
    var loading: (() -> Unit)? = null

    fun<T> NetworkBuilder<T>.executeApi(execute: (suspend () -> T)){
        this.execute = execute
    }

    fun<T> NetworkBuilder<T>.success(success: ((T) -> Unit)){
        this.success = success
    }

    fun<T> NetworkBuilder<T>.error(error: (() -> Unit)){
        this.error = error
    }

    fun<T> NetworkBuilder<T>.loading(loading: (() -> Unit)){
        this.loading = loading
    }
}