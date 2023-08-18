package com.movies.data.remote.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NetworkLauncherImpl : NetworkLauncher {
    override fun <T> startNetwork(
        networkStage: NetworkBuilder<T>.() -> Unit,
        scope: CoroutineScope
    ) {
        scope.launch {
            val builder = NetworkBuilder<T>().apply(networkStage)
            try {
                builder.loading?.invoke(true)
                val execute = builder.execute?.invoke()!!
                builder.success?.invoke(execute)
            } catch (e: Exception) {
                builder.error?.invoke(e)
            }
            builder.loading?.invoke(false)
        }
    }
}