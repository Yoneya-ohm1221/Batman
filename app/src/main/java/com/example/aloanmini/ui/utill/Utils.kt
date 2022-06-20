package com.example.aloanmini.ui.utill

object Utils {
    fun encodeString(string: String): String? {
        return string.replace(".", ",")
    }

    fun decodeString(string: String): String? {
        return string.replace(",", ".")
    }
}