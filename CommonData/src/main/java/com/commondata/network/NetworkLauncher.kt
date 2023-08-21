package com.commondata.network

import kotlinx.coroutines.CoroutineScope

interface NetworkLauncher {
    fun <T> startNetwork(
        networkStage: NetworkBuilder<T>.() -> Unit,
        scope: CoroutineScope
    )
}