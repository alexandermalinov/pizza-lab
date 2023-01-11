package com.example.pizzalab.ui.ingredient

interface IngredientPresenter {

    fun onIngredientClick(ingredientName: String, type: IngredientType) {}
}