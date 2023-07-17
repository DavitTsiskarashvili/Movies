package com.movies.presentation.home.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import com.movies.R
import com.movies.databinding.NavigationCustomViewBinding

class NavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding =
        NavigationCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setButtonsActiveStatus(NavigationButtons.HOME)
        with(binding){
            homeButton.setContent(NavigationButtons.HOME)
            favouritesButton.setContent(NavigationButtons.FAVOURITES)
        }
    }

    fun homeButtonListener(callback: () -> Unit) {
        with(binding) {
            homeButton.setOnClickListener {
                callback()
                setButtonsActiveStatus(NavigationButtons.HOME)
            }
        }
    }

    fun favouritesButtonListener(callback: () -> Unit) {
        with(binding) {
            favouritesButton.setOnClickListener {
                callback()
                setButtonsActiveStatus(NavigationButtons.FAVOURITES)
            }
        }
    }

    private fun setButtonsActiveStatus(buttonType: NavigationButtons) {
        with(binding) {
            when (buttonType) {
                NavigationButtons.HOME -> {
                    homeButton.setActiveButton(true)
                    favouritesButton.setActiveButton(false)
                }

                NavigationButtons.FAVOURITES -> {
                    homeButton.setActiveButton(false)
                    favouritesButton.setActiveButton(true)
                }
            }
        }
    }

}