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

    private var searchCallback: ((String) -> Unit)? = null
    fun setOnSearchListener(callback: (String) -> Unit) {
        searchCallback = callback
    }

    init {
        isFilterChecked()
        setListeners()
    }

    fun queryIsNotBlank() = binding.searchEditText.text?.isNotBlank()

    private fun setListeners() {
        with(binding) {
            with(searchEditText) {
                setOnFocusChangeListener { _, isFocused ->
                    handleViewsVisibility(isFocused)
                }
                doOnTextChanged { query, _, _, _ ->
                    searchCallback?.invoke(query.toString())
                }
            }
            cancelTextView.setOnClickListener {
                searchEditText.text?.clear()
                handleViewsVisibility(false)
            }
        }
    }

    private fun handleViewsVisibility(searchFocused: Boolean) {
        with(binding) {
            filterToggleButton.hiddenIf(searchFocused)
            cancelTextView.visibleIf(searchFocused)
            categoryRecyclerView.hiddenIf(true)
            updateSearchViewConstraints(!searchFocused)
        }
    }

    private fun updateSearchViewConstraints(isFilterVisible: Boolean) {
        with(binding) {
            val params = searchEditText.layoutParams as ConstraintLayout.LayoutParams
            params.endToStart = if (isFilterVisible) filterToggleButton.id else cancelTextView.id
            searchEditText.layoutParams = params
        }
    }

    fun onCategoryButtonClicked(callBack: (CategoryType) -> Unit) {
        categoryAdapter = CategoryAdapter {
            callBack(it)
        }
        initRecycler()
    }

    private fun initRecycler() {
        binding.categoryRecyclerView.adapter = categoryAdapter
        categoryAdapter.setAdapterList(CategoryUIModel.getCategoryList())
    }

    private fun isFilterChecked() {
        with(binding) {
            filterToggleButton.setOnCheckedChangeListener { _, checked ->
                categoryRecyclerView.visibleIf(checked)
            }
        }
    }
}