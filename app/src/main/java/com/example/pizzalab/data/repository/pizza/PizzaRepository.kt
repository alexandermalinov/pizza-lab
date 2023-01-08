package com.example.pizzalab.data.repository.pizza

import com.example.pizzalab.data.local.entity.PizzaWithIngredients
import com.example.pizzalab.data.local.entity.User
import com.example.pizzalab.utils.responsehandler.DatabaseError
import com.example.pizzalab.utils.responsehandler.Either
import com.example.pizzalab.utils.responsehandler.LocalDatabaseError
import com.example.pizzalab.utils.responsehandler.ResponseResultOk
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PizzaRepository @Inject constructor(private val local: PizzaLocalSource) {

    /* --------------------------------------------------------------------------------------------
     * Sources
     ---------------------------------------------------------------------------------------------*/
    interface LocalSource {

        fun getPizzaWithIngredients(): Flow<PizzaWithIngredients>
    }

    fun getPizzaWithIngredients(): Flow<PizzaWithIngredients> = local.getPizzaWithIngredients()
}