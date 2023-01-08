package com.example.pizzalab.vo.createpizza

import com.example.pizzalab.utils.common.EMPTY

data class PizzaUiModel(
    var price: String = "10.0",
    val size: String = EMPTY,
    val ingredients: List<IngredientUiModel> = listOf()
)
