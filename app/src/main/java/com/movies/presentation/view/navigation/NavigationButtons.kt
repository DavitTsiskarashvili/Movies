package com.movies.presentation.view.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.movies.R

enum class NavigationButtons(@StringRes val title: Int, @DrawableRes val icon: Int) {
    LEFT_BUTTON(R.string.home_button, R.drawable.ic_home),
    RIGHT_BUTTON(R.string.favourites_button, R.drawable.ic_favourite_button)
}