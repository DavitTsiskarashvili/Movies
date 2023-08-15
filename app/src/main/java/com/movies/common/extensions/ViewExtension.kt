package com.movies.common.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.movies.presentation.view.search_and_filter.adapter.model.CategoryStyle

fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.visibleIfWithAnimation(visible: Boolean) {
    animation = AnimationUtils.loadAnimation(
        context,
        androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom
    )
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.hiddenIf(visible: Boolean) {
    visibility = if (visible) View.GONE else View.VISIBLE
}

fun View.changeBackgroundColor(@ColorRes colorRes: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, colorRes)
}

fun Button.setStyle(style: CategoryStyle) {
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

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun LinearLayout.replaceView(needsAdd: Boolean, view: View) {
    removeAllViews()
    if (needsAdd) addView(view)
    else removeAllViews()
}

fun EditText.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}