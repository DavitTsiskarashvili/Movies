package com.movies.presentation.home.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
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
        setDefaultButtonTypes()
    }

    fun homeButtonListener(callback: () -> Unit) {
        with(binding) {
            navigationView.setOnClickListener {
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

    private fun setDefaultButtonTypes(){
        with(binding) {
            navigationView.setContent(NavigationButtons.HOME)
            favouritesButton.setContent(NavigationButtons.FAVOURITES)
        }
    }

    private fun setButtonsActiveStatus(buttonType: NavigationButtons) {
        with(binding) {
            when (buttonType) {
                NavigationButtons.HOME -> {
                    navigationView.setActiveButton(true)
                    favouritesButton.setActiveButton(false)
                }
                NavigationButtons.FAVOURITES -> {
                    navigationView.setActiveButton(false)
                    favouritesButton.setActiveButton(true)
                }
            }
        }
    }

}