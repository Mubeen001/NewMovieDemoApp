package com.example.newmoviedemoapp.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


fun Context.hideKeybaord(v: View) {
    val inputMethodManager: InputMethodManager? =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(v.applicationWindowToken, 0)
}

fun Context.showKeyboard(view: View, editetxt : EditText) {
    try {
        editetxt.requestFocus()
        editetxt.postDelayed(
            Runnable {
                val keyboard =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.showSoftInput(editetxt, 0)
            }, 200)
    } catch (npe: NullPointerException) {
        npe.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
