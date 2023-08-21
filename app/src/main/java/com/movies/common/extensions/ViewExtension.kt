package com.movies.common.extensions

import android.view.LayoutInflater
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.movies.presentation.view.search_and_filter.adapter.model.CategoryStyle

fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.goneIf(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.applyAnimation(visible: Boolean) {
    animation = if (visible) {
        AnimationUtils.loadAnimation(
            context,
            androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom
        )
    } else {
        AnimationUtils.loadAnimation(
            context,
            androidx.appcompat.R.anim.abc_shrink_fade_out_from_bottom
        )
    }
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

fun LinearLayout.replaceView(needsAdd: Boolean, view: View) {
    removeAllViews()
    if (needsAdd) addView(view)
    else removeAllViews()
}

fun ImageView.loadImage (url: String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}