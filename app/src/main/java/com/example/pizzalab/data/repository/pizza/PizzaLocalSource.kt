package com.example.pizzalab.data.repository.pizza

import com.example.pizzalab.data.local.dao.PizzaIngredientRelationDao
import com.example.pizzalab.data.local.entity.PizzaWithIngredients
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PizzaLocalSource @Inject constructor(
    private val pizzaDao: PizzaIngredientRelationDao
) : PizzaRepository.LocalSource {

    override fun getPizzaWithIngredients(): Flow<PizzaWithIngredients> =
        pizzaDao.getPizzaWithIngredients()
}