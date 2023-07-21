package com.movies.presentation.home.custom_view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.movies.R

enum class NavigationButtons(@StringRes val title: Int, @DrawableRes val icon: Int) {
    HOME(R.string.home_button, R.drawable.ic_home),
    FAVOURITES(R.string.favourites_button, R.drawable.ic_favourite_button)
}