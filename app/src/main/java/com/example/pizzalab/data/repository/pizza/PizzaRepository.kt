package com.example.pizzalab.data.repository.pizza

import com.example.pizzalab.data.local.entity.Ingredient
import com.example.pizzalab.data.local.entity.Pizza
import com.example.pizzalab.data.local.entity.PizzaWithIngredients
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PizzaRepository @Inject constructor(private val local: PizzaLocalSource) {

    /* --------------------------------------------------------------------------------------------
     * Sources
     ---------------------------------------------------------------------------------------------*/
    interface LocalSource {

        fun getAllPizzasInTheBag(): Flow<List<Pizza>>

        fun getPizza(pizzaId: String): Flow<PizzaWithIngredients>

        suspend fun savePizza(
            id: String,
            title: String,
            description: String,
            price: String,
            size: String,
            ingredientsIds: List<Ingredient>,
            quantity: String
        )

        suspend fun removePizzaFromTheBag(pizzaId: String)
    }

    fun getAllPizzasInBag(): Flow<List<Pizza>> = local.getAllPizzasInTheBag()

    fun getPizza(pizzaId: String): Flow<PizzaWithIngredients> = local.getPizza(pizzaId)

    suspend fun savePizza(
        id: String,
        title: String,
        description: String,
        price: String,
        size: String,
        ingredientsIds: List<Ingredient>,
        quantity: String
    ) {
        local.savePizza(id, title, description, price, size, ingredientsIds, quantity)
    }

    suspend fun removePizzaFromTheBag(pizzaId: String) {
        local.removePizzaFromTheBag(pizzaId)
    }
}