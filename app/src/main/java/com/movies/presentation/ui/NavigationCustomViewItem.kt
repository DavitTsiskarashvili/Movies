package com.movies.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.movies.R
import com.movies.databinding.NavigationCustomViewItemBinding

class NavigationCustomViewItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding =
        NavigationCustomViewItemBinding.inflate(LayoutInflater.from(context), this, true)

    fun setActiveButton(isInFocus: Boolean) {
        if (isInFocus) {
            changeText(R.string.home_button)
            changeDrawable(R.drawable.ic_home)
            changeBackgroundColor(R.color.yellow_primary)
            changeTextColor(R.style.normalMontserrat_ButtonFocused)
        } else {
            changeText(R.string.favourites_button)
            changeDrawable(R.drawable.ic_favourite_button)
            changeBackgroundColor(R.color.neutral_02_darkest_grey)
            changeTextColor(R.style.normalMontserrat_Button)
        }
    }

    private fun changeBackgroundColor(colorRes: Int) {
        binding.root.backgroundTintList = ContextCompat.getColorStateList(context, colorRes)
    }

    private fun changeTextColor(styleRes: Int) {
        binding.pageTextView.setTextAppearance(styleRes)
    }

    private fun changeDrawable(drawableRes: Int) {
        val drawable = ContextCompat.getDrawable(context, drawableRes)
        binding.iconTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
    }

    private fun changeText(textRes: Int) {
        binding.pageTextView.text = context.getString(textRes)
    }

}