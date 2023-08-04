package com.movies.presentation.base.view_model

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.movies.common.navigation.Event
import com.movies.common.navigation.NavigationCommand
import com.movies.common.utils.LiveDataDelegate

abstract class BaseViewModel : ViewModel() {

    val navigationLiveData by LiveDataDelegate<Event<NavigationCommand>>()

    fun navigate(navDirections: NavDirections) {
        navigationLiveData.addValue(Event(NavigationCommand.ToDirection(navDirections)))
    }

    fun navigateUp() {
        navigationLiveData.addValue(Event(NavigationCommand.Back))
    }

}