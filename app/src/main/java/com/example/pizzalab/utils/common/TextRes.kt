package com.example.pizzalab.utils.common

import androidx.annotation.StringRes

data class TextRes(
    val text: String? = EMPTY,
    @StringRes
    val textResource: Int = INVALID_RES
)