package com.example.pizzalab.ui.pizzadetails

interface PizzaDetailsPresenter {

    fun navigateBackClick()

    fun onAddToCartClick()

    fun onSizeClick(size: String)

    fun onMinusClick()

    fun onPlusClick()
}