package com.movies.presentation.view.search_and_filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import com.movies.common.extensions.applyAnimation
import com.movies.common.extensions.goneIf
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.hideKeyboard
import com.movies.common.extensions.visibleIf
import com.movies.common.network.CategoryType
import com.movies.databinding.SearchCustomViewBinding
import com.movies.presentation.view.search_and_filter.adapter.CategoryAdapter
import com.movies.presentation.view.search_and_filter.adapter.model.CategoryUIModel

class SearchAndFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding = SearchCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
    private lateinit var categoryAdapter: CategoryAdapter

    private var searchCallback: ((String) -> Unit)? = null

    fun setOnSearchListener(callback: (String) -> Unit) {
        searchCallback = callback
    }

    var emptySearchCallback: (() -> Unit)? = null

    init {
        isFilterChecked()
        setListeners()
        searchCancelListener()
    }

    fun onCategoryButtonClicked(callBack: (CategoryType) -> Unit) {
        categoryAdapter = CategoryAdapter {
            callBack(it)
        }
        initRecycler()
    }

    private fun isFilterChecked() {
        with(binding) {
            filterToggleButton.setOnCheckedChangeListener { _, checked ->
                categoryRecyclerView.goneIf(checked)
                categoryRecyclerView.applyAnimation(checked)
                clearFocus()
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            searchEditText.doOnTextChanged { query, _, _, _ ->
                searchCallback?.invoke(query.toString())
                handleEmptySearchInput()
            }
        }
    }

    private fun searchCancelListener() = with(binding) {
        cancelTextView.setOnClickListener {
            context.hideKeyboard()
            searchEditText.text?.clear()
            searchEditText.clearFocus()
            filterToggleButton.isChecked = false
            setFilterVisibility(true)
            emptySearchCallback?.invoke()
        }
    }

    override fun clearFocus(){
        binding.searchEditText.clearFocus()
    }

    private fun handleEmptySearchInput() {
        with(binding.searchEditText) {
            if (text!!.isBlank()) {
                setFilterVisibility(true)
                emptySearchCallback?.invoke()
            } else {
                setFilterVisibility(false)
            }
        }
    }

    private fun setFilterVisibility(isVisible: Boolean) {
        with(binding) {
            cancelTextView.hiddenIf(isVisible)
            filterToggleButton.visibleIf(isVisible)
            categoryRecyclerView.goneIf(false)
            categoryRecyclerView.applyAnimation(false)
        }
    }

    private fun initRecycler() {
        binding.categoryRecyclerView.adapter = categoryAdapter
        categoryAdapter.setAdapterList(CategoryUIModel.getCategoryList())
    }

}