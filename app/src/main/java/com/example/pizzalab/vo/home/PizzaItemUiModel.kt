package com.example.pizzalab.vo.home

import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.INVALID_RES
import com.example.pizzalab.vo.createpizza.IngredientUiModel
import java.util.*

data class PizzaItemUiModel(
    val id: String = UUID.randomUUID().toString(),
    val image: Int = INVALID_RES,
    val title: String = EMPTY,
    val description: String = EMPTY,
    val price: String = EMPTY,
    val size: String = EMPTY,
    val ingredients: List<IngredientUiModel> = listOf()
)
