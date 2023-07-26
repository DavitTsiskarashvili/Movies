package com.movies.presentation.model.category

import androidx.annotation.StringRes
import com.movies.common.network.CategoryType

data class Category(
    @StringRes val name: Int,
    val categoryType: CategoryType,
    var isClicked: Boolean
)