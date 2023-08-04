package com.movies.data.remote.network

import com.movies.presentation.view.loader.LoaderDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface NetworkLauncherApi {
    fun <T> startNetwork(
        networkStage: NetworkBuilder<T>.() -> Unit,
        scope: CoroutineScope
    )
}

class NetworkLauncher(
    private val loader: LoaderDialog
) : NetworkLauncherApi {
    override fun <T> startNetwork(
        networkStage: NetworkBuilder<T>.() -> Unit,
        scope: CoroutineScope
    ) {
        scope.launch {
            val builder = NetworkBuilder<T>().apply(networkStage)
            try {
                builder.loading?.invoke()
                val execute = builder.execute?.invoke()!!
                builder.success?.invoke(execute)

            } catch (e: Exception) {
                builder.error?.invoke()
            }
        }
    }
}