package com.movies.presentation.view.navigation

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
        setButtonsActiveStatus(NavigationButtons.LEFT_BUTTON)
        setDefaultButtonTypes()
    }

    fun leftButtonListener(callback: () -> Unit) {
        with(binding) {
            leftButton.setOnClickListener {
                callback()
                setButtonsActiveStatus(NavigationButtons.LEFT_BUTTON)
            }
        }
    }

    fun rightButtonListener(callback: () -> Unit) {
        with(binding) {
            rightButton.setOnClickListener {
                callback()
                setButtonsActiveStatus(NavigationButtons.RIGHT_BUTTON)
            }
        }
    }

    private fun setDefaultButtonTypes(){
        with(binding) {
            leftButton.setContent(NavigationButtons.LEFT_BUTTON)
            rightButton.setContent(NavigationButtons.RIGHT_BUTTON)
        }
    }

    private fun setButtonsActiveStatus(buttonType: NavigationButtons) {
        with(binding) {
            when (buttonType) {
                NavigationButtons.LEFT_BUTTON -> {
                    leftButton.setActiveButton(true)
                    rightButton.setActiveButton(false)
                }
                NavigationButtons.RIGHT_BUTTON -> {
                    leftButton.setActiveButton(false)
                    rightButton.setActiveButton(true)
                }
            }
        }
    }

}