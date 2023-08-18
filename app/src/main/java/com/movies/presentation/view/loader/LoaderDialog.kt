package com.movies.presentation.view.loader

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.movies.databinding.LoaderDialogBinding

class LoaderDialog(
    context: Context,
    attributeSet: AttributeSet,
) : FrameLayout(context, attributeSet) {
     private val binding: LoaderDialogBinding =
         LoaderDialogBinding.inflate(LayoutInflater.from(context), this, false)

     private val alertDialog: AlertDialog by lazy{
         AlertDialog.Builder(context).setView(binding.root).create()
     }

    init {
        setupDialog().apply {
            setCancelable(false)
        }
    }

    private fun setupDialog(): AlertDialog {
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return alertDialog
    }

    fun initiateDialog(isLoading: Boolean) {
        Log.d("TAG_LOADER", isLoading.toString())
        if (isLoading)
            alertDialog.show()
        else
            alertDialog.dismiss()
    }

}