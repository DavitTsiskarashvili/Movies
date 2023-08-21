package com.movies.presentation.view.search_and_filter.adapter.model

import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import com.movies.common.utils.D
import com.movies.common.utils.S

enum class CategoryStyle(
    @StyleRes val style: Int,
    @DrawableRes val strokeStyle: Int
) {
    ACTIVE(
        style = S.smallMontserrat_ButtonFocused,
        strokeStyle = D.background_filter_focused
    ),
    INACTIVE(
        style = S.smallMontserrat_Button,
        strokeStyle = D.background_filter_unfocused
    )
}