package com.commonpresentation.presentation.view.navigation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.commonpresentation.databinding.NavigationCustomViewItemBinding
import com.commonpresentation.extensions.changeBackgroundColor
import com.commonpresentation.extensions.changeDrawable
import com.commonpresentation.extensions.changeDrawableColor
import com.commonpresentation.extensions.changeText
import com.commonpresentation.extensions.changeTextStyle
import com.commonpresentation.utils.C
import com.commonpresentation.utils.S

class NavigationViewItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttrs, defStyleRes) {

    private val binding =
        NavigationCustomViewItemBinding.inflate(LayoutInflater.from(context), this, true)

    fun setActiveButton(isActive: Boolean) {
        val backgroundColor = if (isActive) ACTIVE_BACKGROUND_COLOR else PASSIVE_BACKGROUND_COLOR
        val textStyle = if (isActive) ACTIVE_TEXT_STYLE else PASSIVE_TEXT_STYLE
        val drawableColor = if (isActive) ACTIVE_DRAWABLE_COLOR else PASSIVE_DRAWABLE_COLOR

        with(binding) {
            root.changeBackgroundColor(backgroundColor)
            pageTextView.changeTextStyle(textStyle)
            iconImageView.changeDrawableColor(drawableColor)
        }
    }

    fun setContent(buttonType: NavigationButtons) {
        with(binding) {
            iconImageView.changeDrawable(buttonType.icon)
            pageTextView.changeText(buttonType.title)
        }
    }

    private companion object {
        val ACTIVE_BACKGROUND_COLOR = C.yellow_primary
        val PASSIVE_BACKGROUND_COLOR = C.neutral_02_darkest_grey

        val ACTIVE_TEXT_STYLE = S.normalMontserrat_ButtonFocused
        val PASSIVE_TEXT_STYLE = S.normalMontserrat_Button

        val ACTIVE_DRAWABLE_COLOR = C.neutral_01_black
        val PASSIVE_DRAWABLE_COLOR = C.neutral_08_whisper
    }

}