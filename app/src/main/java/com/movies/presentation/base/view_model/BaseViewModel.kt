package com.movies.presentation.base.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.data.remote.network.NetworkBuilder
import com.movies.data.remote.network.NetworkLauncher
import com.movies.presentation.base.data.ui_state.UIState
import org.koin.java.KoinJavaComponent.inject

abstract class BaseViewModel<T> : ViewModel() {

    open fun onCreate() {}

    protected val _uiStateLiveData = MutableLiveData<UIState<T>>()
    val uiStateLiveData: LiveData<UIState<T>> get() = _uiStateLiveData

    private val networkLauncher = inject<NetworkLauncher>(NetworkLauncher::class.java).value

    protected fun <T> Any.launchNetwork(networkStage: NetworkBuilder<T>.() -> Unit) {
        networkLauncher.startNetwork<T>(scope = viewModelScope, networkStage = networkStage)
    }

}