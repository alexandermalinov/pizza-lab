package com.example.pizzalab.vo.home

import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.TextRes

data class PizzaItemUiModel(
    val image: String = EMPTY,
    val title: String = EMPTY,
    val description: String = EMPTY,
    val price: TextRes = TextRes()
)
