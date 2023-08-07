package com.movies.presentation.view.loader

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.R

class LoaderDialog(
    val context: Context,
    val root: ViewGroup
) {
    private lateinit var alertDialog: AlertDialog

    init {
        setupDialog().apply {
            setCancelable(false)
        }
    }

    private fun setupDialog(): AlertDialog {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.loader_dialog, root)
        alertDialog = AlertDialog.Builder(context).setView(view).create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return alertDialog
    }

    fun initiateDialog(isLoading: Boolean) {
        Log.d("TAG_LOADER", isLoading.toString())
        if (isLoading) alertDialog.show() else alertDialog.dismiss()
    }

}