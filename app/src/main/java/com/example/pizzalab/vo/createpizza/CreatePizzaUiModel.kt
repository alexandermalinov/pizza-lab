package com.example.pizzalab.vo.createpizza

data class CreatePizzaUiModel(
    val meats: List<IngredientUiModel> = emptyList(),
    val vegetables: List<IngredientUiModel> = emptyList(),
    val cheeses: List<IngredientUiModel> = emptyList(),
    val spices: List<IngredientUiModel> = emptyList(),
    val sauces: List<IngredientUiModel> = emptyList()
)
