package com.movies.presentation.base.fragment.listener

import android.view.View
import com.movies.presentation.view.navigation.NavigationButtons

interface BaseFunctionsInterface {
    fun showBottomView() = true
    fun needPressBack() = true
    fun bottomView(): View
    fun defaultLeftButtonAction() {}
    fun defaultRightButtonAction() {}
    fun customResultHandler() {}
    fun activeNavigationButton(): NavigationButtons = NavigationButtons.LEFT_BUTTON
    fun resultKey(): String? = null
}