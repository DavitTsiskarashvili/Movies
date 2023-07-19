package com.movies.presentation.home.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.movies.common.extensions.setVisibility
import com.movies.databinding.SearchCustomViewBinding

class SearchAndFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding = SearchCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        isFilterChecked()
    }

    fun popularButtonListener(callback: () -> Unit) {
        with(binding) {
            popularButton.setOnClickListener {
                callback()
            }
        }
    }

    fun topRatedButtonListener(callback: () -> Unit) {
        with(binding) {
            topRatedButton.setOnClickListener {
                callback()
            }
        }
    }

    private fun isFilterChecked() {
        with(binding) {
            filterToggleButton.setOnCheckedChangeListener { _, checked ->
                filterViewGroup.setVisibility(checked)
            }
        }
    }

}