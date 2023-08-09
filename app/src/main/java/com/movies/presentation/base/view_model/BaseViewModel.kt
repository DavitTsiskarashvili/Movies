package com.movies.presentation.base.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.movies.common.navigation.Event
import com.movies.common.navigation.NavigationCommand
import com.movies.common.utils.LiveDataDelegate
import com.movies.data.remote.network.NetworkBuilder
import com.movies.data.remote.network.NetworkLauncher
import com.movies.presentation.base.data.ui_state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.java.KoinJavaComponent.inject

abstract class BaseViewModel<T> : ViewModel() {

    open fun onCreate() {}

    val navigationLiveData by LiveDataDelegate<Event<NavigationCommand>>()
    protected  val _uiStateFlow = MutableStateFlow<UIState<T>?>(null)
    val uiStateFlow get() = _uiStateFlow.asStateFlow()

    private val networkLauncher = inject<NetworkLauncher>(NetworkLauncher::class.java).value

    fun navigate(navDirections: NavDirections) {
        navigationLiveData.addValue(Event(NavigationCommand.ToDirection(navDirections)))
    }

    fun navigateUp() { navigationLiveData.addValue(Event(NavigationCommand.Back)) }

    protected fun <T> Any.launchNetwork(networkStage: NetworkBuilder<T>.() -> Unit) {
        networkLauncher.startNetwork<T>(scope = viewModelScope, networkStage = networkStage)
    }

}