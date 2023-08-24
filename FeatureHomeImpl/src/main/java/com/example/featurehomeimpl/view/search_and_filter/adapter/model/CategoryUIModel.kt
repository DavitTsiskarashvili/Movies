package com.example.featurehomeimpl.view.search_and_filter.adapter.model

import androidx.annotation.StringRes
import com.example.featurehomeimpl.R

data class CategoryUIModel(
    @StringRes val name: Int,
    val categoryType: CategoryType,
    var isClicked: Boolean
) {
    companion object {
        fun getCategoryList(): List<CategoryUIModel> {
            return listOf(
                CategoryUIModel(R.string.popular, CategoryType.POPULAR, true),
                CategoryUIModel(R.string.top_rated, CategoryType.TOP_RATED, false),
                CategoryUIModel(R.string.now_playing, CategoryType.NOW_PLAYING, false),
                CategoryUIModel(R.string.upcoming, CategoryType.UPCOMING, false),
            )
        }
    }

}