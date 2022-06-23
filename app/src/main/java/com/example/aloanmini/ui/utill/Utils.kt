package com.example.aloanmini.ui.utill

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun encodeString(string: String): String? {
        return string.replace(".", ",")
    }

    fun decodeString(string: String): String? {
        return string.replace(",", ".")
    }

    fun dateTimeNow(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return sdf.format(Date())
    }

}