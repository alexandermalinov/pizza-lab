package com.example.pizzalab.ui.home

interface PizzaPresenter {

    fun onAddToBag(pizzaId: String)

    fun onPizzaClick(pizzaId: String)
}