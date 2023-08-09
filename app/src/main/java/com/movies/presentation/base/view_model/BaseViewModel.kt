package com.movies.presentation.base.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.movies.common.navigation.Event
import com.movies.common.navigation.NavigationCommand
import com.movies.common.utils.LiveDataDelegate
import com.movies.data.remote.network.NetworkBuilder
import com.movies.data.remote.network.NetworkLauncher
import com.movies.presentation.base.data.ui_state.UIState
import org.koin.java.KoinJavaComponent.inject

abstract class BaseViewModel<T> : ViewModel() {

    open fun onCreate() {}

    val navigationLiveData by LiveDataDelegate<Event<NavigationCommand>>()
    protected  val _uiStateLiveData =  MutableLiveData<UIState<T>>()
    val uiStateLiveData:LiveData<UIState<T>> get() = _uiStateLiveData

    private val networkLauncher = inject<NetworkLauncher>(NetworkLauncher::class.java).value

    fun navigate(navDirections: NavDirections) {
        navigationLiveData.addValue(Event(NavigationCommand.ToDirection(navDirections)))
    }

    fun navigateUp() { navigationLiveData.addValue(Event(NavigationCommand.Back)) }

    protected fun <T> Any.launchNetwork(networkStage: NetworkBuilder<T>.() -> Unit) {
        networkLauncher.startNetwork<T>(scope = viewModelScope, networkStage = networkStage)
    }

}