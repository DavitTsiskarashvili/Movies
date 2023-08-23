package com.movies.presentation.view.search_and_filter.adapter.model

import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import com.movies.common.utils.C
import com.movies.common.utils.S

enum class CategoryStyle(
    @StyleRes val style: Int,
    @ColorRes val backgroundColor: Int

) {
    ACTIVE(
        style = S.smallMontserrat_ButtonFocused,
        backgroundColor = C.yellow_primary

    ),
    INACTIVE(
        style = S.smallMontserrat_Button,
        backgroundColor = C.neutral_02_darkest_grey
    )
}