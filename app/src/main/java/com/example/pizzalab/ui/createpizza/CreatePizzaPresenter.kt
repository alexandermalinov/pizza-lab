package com.example.pizzalab.ui.createpizza

interface CreatePizzaPresenter {

    fun onSizeClick(isChecked: Boolean, size: String)

    fun onPurchaseClick()
}