package com.movies.presentation.view.error

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.databinding.ErrorCustomViewBinding

class ErrorView(
    val context: Context,
    root: ViewGroup
) {
    private var alertDialog: AlertDialog? = null
    private val binding =
        ErrorCustomViewBinding.inflate(LayoutInflater.from(context), root, false)

    init {
        setupDialog()
    }

    private fun setupDialog() {
        alertDialog = AlertDialog.Builder(context)
            .setView(binding.root)
            .create().apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    fun handleErrorVisibility(isError: Boolean) {
        if (isError) alertDialog?.show()
        else alertDialog?.dismiss()
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