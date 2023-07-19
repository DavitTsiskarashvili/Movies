package com.movies.presentation.home.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import com.movies.common.extensions.changeBackgroundColor
import com.movies.common.extensions.changeTextStyle
import com.movies.common.extensions.setVisibility
import com.movies.common.network.Category
import com.movies.common.utils.C
import com.movies.common.utils.S
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

    fun categorySelectedListener(category: Category, callback:(Category) -> Unit){
        when (category) {
            Category.POPULAR -> {
                popularButtonListener { callback(category) }
            }
            Category.TOP_RATED -> {
                topRatedButtonListener { callback(category) }
            }
        }
    }

    private fun popularButtonListener(callback: () -> Unit) {
        with(binding) {
            popularButton.setOnClickListener {
                callback()
                popularButton.setActiveButton(true)
                topRatedButton.setActiveButton(false)
            }
        }
    }

    private fun topRatedButtonListener(callback: () -> Unit) {
        with(binding) {
            topRatedButton.setOnClickListener {
                callback()
                popularButton.setActiveButton(false)
                topRatedButton.setActiveButton(true)
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

    private fun Button.setActiveButton(isActive: Boolean) {

        val backgroundColor = if (isActive) ACTIVE_BACKGROUND_COLOR else PASSIVE_BACKGROUND_COLOR
        val textStyle = if (isActive) ACTIVE_TEXT_STYLE else PASSIVE_TEXT_STYLE
            changeBackgroundColor(backgroundColor)
            changeTextStyle(textStyle)
            changeTextStyle(textStyle)

    }

    companion object {
        private val ACTIVE_BACKGROUND_COLOR = C.yellow_primary
        private val PASSIVE_BACKGROUND_COLOR = C.neutral_02_darkest_grey

        private val ACTIVE_TEXT_STYLE = S.smallMontserrat_ButtonFocused
        private val PASSIVE_TEXT_STYLE = S.smallMontserrat_Button
    }


}