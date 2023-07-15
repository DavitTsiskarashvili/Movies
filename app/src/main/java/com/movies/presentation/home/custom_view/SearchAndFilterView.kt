package com.movies.presentation.home.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.movies.R
import com.movies.common.extensions.setVisibility
import com.movies.databinding.SearchCustomViewBinding

class SearchAndFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes)  {

    private val binding = SearchCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun isFilterChecked(checked: Boolean) {
        with(binding) {
            filterImageView.setOnClickListener{
                if (checked){
                    changeDrawable(R.drawable.ic_filter_clicked)
                    filterViewGroup.setVisibility(true)
                } else {
                    filterViewGroup.setVisibility(false)
                }
            }
        }
    }

    private fun changeDrawable(drawableRes: Int) {
        val drawable = ContextCompat.getDrawable(context, drawableRes)
        binding.filterImageView.setImageDrawable(drawable)
    }


}