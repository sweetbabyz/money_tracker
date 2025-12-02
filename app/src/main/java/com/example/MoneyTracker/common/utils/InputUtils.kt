package com.example.jizhangbao.common.utils

import com.google.android.material.textfield.TextInputEditText

 fun checkInput(input: TextInputEditText): Boolean {
    return input.text.isNullOrBlank()
}