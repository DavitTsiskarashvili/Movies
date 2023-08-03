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
import com.movies.presentation.home.adapter.category.CategoryAdapter
import com.movies.presentation.model.category.Category

class SearchAndFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding = SearchCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val categoryAdapter by lazy { CategoryAdapter() }
    val searchInput get() = binding.searchEditText.text.toString()

    init {
        isFilterChecked()
        initRecycler()
    }

    fun searchListener(listener: (String) -> Unit) {
        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank().not()) {
                listener(text.toString())
                handleViewsVisibility(true)
                updateSearchViewConstraints(false)
                emptyInputHandler()
            }
        }
        clearSearchInput()
    }

    private fun emptyInputHandler() {
        if (binding.searchEditText.text?.isEmpty() == true) {
            handleViewsVisibility(false)
            updateSearchViewConstraints(true)
        }
    }

    private fun clearSearchInput() {
        binding.cancelTextView.setOnClickListener {
            binding.searchEditText.text?.clear()
            handleViewsVisibility(false)
            updateSearchViewConstraints(true)
        }
    }

    private fun handleViewsVisibility(searchIsClicked: Boolean) {
        with(binding) {
            filterToggleButton.hiddenIf(searchIsClicked)
            categoryRecyclerView.hiddenIf(searchIsClicked)
            cancelTextView.visibleIf(searchIsClicked)
        }
    }

    private fun updateSearchViewConstraints(isFilterVisible: Boolean) {
        with(binding) {
            val params = searchEditText.layoutParams as ConstraintLayout.LayoutParams
            params.endToStart = if (isFilterVisible) filterToggleButton.id else cancelTextView.id
            searchEditText.layoutParams = params
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