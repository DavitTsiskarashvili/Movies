package com.movies.presentation.view.error

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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

    private lateinit var alertDialog: AlertDialog

    init {
        setupDialog()
    }

    private fun setupDialog() {
        alertDialog = AlertDialog.Builder(context).setView(this).create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun handleErrorVisibility(isError: Boolean) {
        if (isError) alertDialog.show()
        else alertDialog.dismiss()
    }

    fun refreshButtonListener(callback: () -> Unit) {
        with(binding) {
            refreshButton.setOnClickListener {
                callback()
                handleErrorVisibility(false)
            }
        }
    }

}