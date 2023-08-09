package com.movies.presentation.view.loader

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.movies.R

class LoaderDialog(
    val context: Context,
    val root: ViewGroup
) {
    private var alertDialog: AlertDialog? = null
    private val loaderView: View =
        LayoutInflater.from(context).inflate(R.layout.loader_dialog, root, false)

    init { setupDialog() }

    private fun setupDialog() {
        alertDialog = AlertDialog.Builder(context)
            .setView(loaderView)
            .create().apply {
                setCancelable(false)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    fun handleLoaderVisibility(isLoading: Boolean) {
        if (isLoading) alertDialog?.show()
        else alertDialog?.dismiss()
    }

}