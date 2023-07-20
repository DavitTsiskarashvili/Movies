package com.movies.presentation.home.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import com.movies.common.extensions.visibleIf
import com.movies.databinding.SearchCustomViewBinding
import com.movies.presentation.home.CategoryList
import com.movies.presentation.home.adapter.CategoryAdapter
import com.movies.presentation.model.category.Category

class SearchAndFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding = SearchCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val categoryAdapter by lazy { CategoryAdapter() }

    init {
        isFilterChecked()
        initRecycler()
    }

    fun searchListener(callback: (String) -> Unit) {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            callback(text?.toString() ?: "")
        }
    }

    fun categoryButtonListener(callback: (Category) -> Unit) {
        categoryAdapter.onItemClickListener {
            callback(it)
            CategoryList.editActiveCategories(it)
            categoryAdapter.submitList(CategoryList.getCategories())
            categoryAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecycler() {
        binding.categoryRecyclerView.adapter = categoryAdapter
        categoryAdapter.submitList(CategoryList.getCategories())
    }

    private fun isFilterChecked() {
        with(binding) {
            filterToggleButton.setOnCheckedChangeListener { _, checked ->
                categoryRecyclerView.visibleIf(checked)
            }
        }
    }

}