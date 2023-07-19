package com.movies.presentation.home

import android.util.Log
import com.movies.R
import com.movies.common.network.CategoryType
import com.movies.presentation.model.category.Category

object CategoryList{
    private val categories = listOf(
        Category(R.string.popular,  CategoryType.POPULAR, true),
        Category(R.string.top_rated, CategoryType.TOP_RATED, false)
    )

    fun getCategories(): List<Category> {
        return categories
    }

    fun editActiveCategories(category: Category){
        for (i in categories) {
            i.isClicked = i == category
        }
        Log.d("listi", "$categories")
    }
}