package com.movies.presentation.home.custom_view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.movies.R
import com.movies.common.utils.D
import com.movies.common.utils.T

enum class NavigationButtons(@StringRes val title: Int, @DrawableRes val icon: Int) {
    LEFT_BUTTON(T.home_button, D.ic_home),
    RIGHT_BUTTON(T.favourites_button, D.ic_favourite_button)
}