package com.movies.presentation.home.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.movies.common.extensions.hiddenIf
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
            handleViews(true)
            updateSearchViewConstraints(false)

        }
        clearSearchInput()
    }

    private fun clearSearchInput() {
        binding.cancelTextView.setOnClickListener {
            binding.searchEditText.text?.clear()
            handleViews(false)
            updateSearchViewConstraints(true)
        }
    }

    private fun handleViews(searchIsClicked: Boolean) {
        with(binding) {
            filterToggleButton.hiddenIf(searchIsClicked)
            cancelTextView.visibleIf(searchIsClicked)
        }
    }

    private fun updateSearchViewConstraints(isFilterVisible: Boolean) {
        val params = binding.searchEditText.layoutParams as ConstraintLayout.LayoutParams
        if (isFilterVisible) {
            params.endToStart = binding.filterToggleButton.id
        } else {
            params.endToStart = binding.cancelTextView.id
        }
        binding.searchEditText.layoutParams = params
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