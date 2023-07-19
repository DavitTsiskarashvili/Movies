package com.movies.presentation.home.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.movies.databinding.ErrorCustomViewBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding =
        ErrorCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun refreshButtonListener(callback: () -> Unit) {
        with(binding) {
            refreshButton.setOnClickListener {
                callback()
            }
        }
    }

}