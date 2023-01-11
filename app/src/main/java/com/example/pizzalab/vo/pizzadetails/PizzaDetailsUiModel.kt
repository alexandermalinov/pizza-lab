package com.example.pizzalab.vo.pizzadetails

import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.ZERO
import com.example.pizzalab.vo.createpizza.PizzaUiModel

data class PizzaDetailsUiModel(
    val pizza: PizzaUiModel = PizzaUiModel(),
    val quantity: String = EMPTY,
)
