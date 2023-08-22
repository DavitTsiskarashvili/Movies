package com.example.featurehomeimpl.view.search_and_filter.extension

import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.featurehomeimpl.view.search_and_filter.adapter.model.CategoryStyle

fun Button.setStyle(style: CategoryStyle) {
    setTextAppearance(style.style)
    backgroundTintList = ContextCompat.getColorStateList(context, style.backgroundColor)
}