package com.example.pizzalab.vo.createpizza

import com.example.pizzalab.data.local.entity.Pizza
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.INVALID_RES

data class PizzaUiModel(
    val id: String = EMPTY,
    var price: String = "10.0",
    val size: String = EMPTY,
    val ingredients: List<IngredientUiModel> = listOf(),
    val title: String = "Your Own Made",
    val description: String = ingredients.joinToString("•"),
    val quantity: String = "1",
    val imageRes: Int = INVALID_RES
)

fun List<PizzaUiModel>.toListOfPizza() = map { pizzaUiModel ->
    Pizza(
        id = pizzaUiModel.id,
        price = pizzaUiModel.price,
        size = pizzaUiModel.size,
        ingredients = pizzaUiModel.ingredients.toListOfIngredients(),
        title = pizzaUiModel.title,
        description = pizzaUiModel.description,
        quantity = pizzaUiModel.quantity,
        image = pizzaUiModel.imageRes
    )
}
