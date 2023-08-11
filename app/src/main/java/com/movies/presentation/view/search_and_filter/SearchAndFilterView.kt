package com.movies.presentation.view.search_and_filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.visibleIf
import com.movies.common.network.CategoryType
import com.movies.databinding.SearchCustomViewBinding
import com.movies.presentation.view.search_and_filter.adapter.CategoryAdapter
import com.movies.presentation.view.search_and_filter.adapter.model.CategoryUIModel

class SearchAndFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding = SearchCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
    private lateinit var categoryAdapter: CategoryAdapter
    val searchInput get() = binding.searchEditText.text.toString()
    var isSearched: Boolean = false

    private var searchCallback: ((String, Boolean) -> Unit)? = null
    fun setOnSearchListener(callback: (String, Boolean) -> Unit) {
        searchCallback = callback
    }

    init {
        isFilterChecked()
        setListeners()
    }

    fun queryIsNotBlank() = binding.searchEditText.text?.isNotBlank()

    fun onCategoryButtonClicked(callBack: (CategoryType) -> Unit) {
        categoryAdapter = CategoryAdapter {
            callBack(it)
        }
        initRecycler()
    }

    private fun isFilterChecked() {
        with(binding) {
            filterToggleButton.setOnCheckedChangeListener { _, checked ->
                categoryRecyclerView.visibleIf(checked)
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            searchEditText.doOnTextChanged { query, _, _, _ ->
                if (searchEditText.text.isNullOrBlank())
                isSearched = true
                searchCallback?.invoke(query.toString(), isSearched)
                handleEmptySearchInput()
            }
            cancelTextView.setOnClickListener {
                searchEditText.text?.clear()
                handleEmptySearchInput()
            }
        }
    }

    private fun handleEmptySearchInput() {
        with(binding.searchEditText) {
            if (text!!.isBlank()) {
                setFilterVisibility(true)
            } else {
                setFilterVisibility(false)
            }
        }
    }

    private fun setFilterVisibility(isVisible: Boolean) {
        with(binding) {
            cancelTextView.hiddenIf(isVisible)
            filterToggleButton.visibleIf(isVisible)
            categoryRecyclerView.visibleIf(false)
            updateSearchViewConstraints(!isVisible)
        }
    }

    private fun updateSearchViewConstraints(searchFocused: Boolean) {
        with(binding) {
            val params = searchEditText.layoutParams as ConstraintLayout.LayoutParams
            params.endToStart = if (searchFocused) cancelTextView.id else filterToggleButton.id
            searchEditText.layoutParams = params
        }
    }

    private fun initRecycler() {
        binding.categoryRecyclerView.adapter = categoryAdapter
        categoryAdapter.setAdapterList(CategoryUIModel.getCategoryList())
    }

}