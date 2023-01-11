package com.example.pizzalab.vo.ordersuccessful

import com.example.pizzalab.utils.common.EMPTY
import java.util.Date

data class OrderSuccessfulUiModel(
    val address: String = EMPTY,
    val totalPrice: String = EMPTY
)
