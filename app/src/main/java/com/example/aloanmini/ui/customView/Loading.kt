package com.example.aloanmini.ui.customView

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.aloanmini.R

object Loading {

    fun setProgressDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.loading)
       return dialog
    }
}