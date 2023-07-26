package com.movies.presentation.loader

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.movies.R

class LoaderDialog(
    val context: Context,
) {
    private lateinit var alertDialog: AlertDialog

    init {
        setupDialog()
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
    }

    private fun setupDialog() {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.loader_dialog, null)
        alertDialog = AlertDialog.Builder(context).setView(view).create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun initiateDialog(isLoading: Boolean) {
        if (isLoading) alertDialog.show() else alertDialog.dismiss()
    }

}