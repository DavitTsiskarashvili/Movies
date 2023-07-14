package com.movies.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.movies.databinding.NavigationCustomViewBinding

class NavigationCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding =
        NavigationCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(binding) {
            homeButton.setActiveButton(true)
            favouritesButton.setActiveButton(false)
        }
    }

    fun leftButtonListener(callback: () -> Unit) {
        with(binding) {
            homeButton.setOnClickListener {
                callback()
                homeButton.setActiveButton(true)
                favouritesButton.setActiveButton(false)
            }
        }
    }

    fun rightButtonListener(callback: () -> Unit) {
        with(binding) {
            favouritesButton.setOnClickListener {
                callback()
                favouritesButton.setActiveButton(true)
                homeButton.setActiveButton(false)
            }
        }
    }

}