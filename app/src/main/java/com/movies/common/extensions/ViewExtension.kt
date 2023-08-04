package com.movies.common.extensions

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.movies.presentation.view.search_and_filter.adapter.model.CategoryStyle

fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.hiddenIf(visible: Boolean) {
    visibility = if (visible) View.GONE else View.VISIBLE
}

fun View.invisibleIf(visible: Boolean) {
    visibility = if (visible) View.INVISIBLE else View.VISIBLE
}

fun View.changeBackgroundColor(@ColorRes colorRes: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, colorRes)
}

fun Button.setCategoryStyle(style: CategoryStyle) {
    setTextAppearance(style.style)
    backgroundTintList = ContextCompat.getColorStateList(context, style.backgroundColor)
}

fun TextView.changeTextStyle(@StyleRes styleRes: Int) {
    setTextAppearance(styleRes)
}

fun TextView.changeText(@StringRes textRes: Int) {
    text = context.getString(textRes)
}

fun ImageView.changeDrawable(@DrawableRes drawableRes: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
}

fun ImageView.changeDrawableColor(@ColorRes colorRes: Int) {
    imageTintList = ContextCompat.getColorStateList(context, colorRes)
}