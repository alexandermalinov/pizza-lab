package com.example.pizzalab.data.local.model

import com.example.pizzalab.ui.ingredient.IngredientType
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.ZERO

data class Ingredient(
    val image: Int = ZERO,
    val name: String = EMPTY,
    val type: IngredientType = IngredientType.MEAT
)
