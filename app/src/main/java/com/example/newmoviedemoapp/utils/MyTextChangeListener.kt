package com.example.newmoviedemoapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.newmoviedemoapp.interfaces.onTextChanged

class MyTextChangeListener(editText: EditText, var onTextChange: onTextChanged) :TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        onTextChange.onTextChange(s!!)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

}