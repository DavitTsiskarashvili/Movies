package com.movies.presentation.base.view_model

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.movies.common.navigation.Event
import com.movies.common.utils.LiveDataDelegate
import com.movies.presentation.loader.LoaderDialog
import com.movies.presentation.navigation.NavigationCommand
import org.koin.java.KoinJavaComponent.get

abstract class BaseViewModel : ViewModel() {

    open fun onCreate() {}

    val navigationLiveData by LiveDataDelegate<Event<NavigationCommand>>()
    protected val loader: LoaderDialog = get(LoaderDialog::class.java)

    fun navigate(navDirections: NavDirections) {
        navigationLiveData.addValue(Event(NavigationCommand.ToDirection(navDirections)))
    }

    fun navigateUp() {
        navigationLiveData.addValue(Event(NavigationCommand.Back))
    }

}