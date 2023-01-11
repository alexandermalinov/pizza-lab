package com.example.pizzalab.vo.home

import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.INVALID_RES
import com.example.pizzalab.vo.createpizza.IngredientUiModel
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class PizzaItemUiModel(
    val id: String = UUID.randomUUID().toString(),
    val image: Int = INVALID_RES,
    val title: String = EMPTY,
    val description: String = EMPTY,
    val price: String = EMPTY,
    val size: String = EMPTY,
    val ingredients: List<IngredientUiModel> = listOf(),
    val quantity: String = "1"
) : java.io.Serializable
