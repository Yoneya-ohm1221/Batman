package com.example.aloanmini.ui.customView

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.aloanmini.R

class Loading( context: Context){
    private val dialog = Dialog(context)

    init {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.loading)
    }

    fun show(){
        try {
            dialog.show()
        }catch (e:Exception){}
    }

    fun dismiss(){
        dialog.dismiss()
    }
}