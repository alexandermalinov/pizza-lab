package com.example.pizzalab.data.repository.pizza

import com.example.pizzalab.data.local.dao.IngredientsDao
import com.example.pizzalab.data.local.dao.PizzaDao
import com.example.pizzalab.data.local.dao.PizzaIngredientRelationDao
import com.example.pizzalab.data.local.entity.Ingredient
import com.example.pizzalab.data.local.entity.Pizza
import com.example.pizzalab.data.local.entity.PizzaIngredientCrossRef
import com.example.pizzalab.data.local.entity.PizzaWithIngredients
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PizzaLocalSource @Inject constructor(
    private val pizzaIngredientRelationDao: PizzaIngredientRelationDao,
    private val pizzaDao: PizzaDao,
    private val ingredientsDao: IngredientsDao
) : PizzaRepository.LocalSource {

    /* --------------------------------------------------------------------------------------------
     * Override
     ---------------------------------------------------------------------------------------------*/
    override fun getAllPizzasInTheBag(): Flow<List<Pizza>> = pizzaDao.getPizzasInTheBag()

    override fun getPizza(pizzaId: String): Flow<PizzaWithIngredients> =
        pizzaIngredientRelationDao.getPizzaWithIngredients(pizzaId)

    override suspend fun savePizza(
        id: String,
        title: String,
        description: String,
        price: String,
        size: String,
        ingredientsIds: List<Ingredient>
    ) {
        ingredientsDao.insert(ingredientsIds)
        savePizzaWithIngredients(id, ingredientsIds.map { it.id })
        pizzaDao.savePizza(
            Pizza(
                id = id,
                price = price,
                size = size,
                ingredients = ingredientsIds,
                title = title,
                description = description
            )
        )
    }

    override suspend fun removePizzaFromTheBag(pizzaId: String) {
        pizzaIngredientRelationDao.removePizzaWithIngredientFromBag(pizzaId)
        pizzaDao.removePizzaFromBag(pizzaId)
    }

    /* --------------------------------------------------------------------------------------------
     * Private
     ---------------------------------------------------------------------------------------------*/
    private suspend fun savePizzaWithIngredients(pizzaId: String, ingredientsIds: List<String>) {
        ingredientsIds.map { PizzaIngredientCrossRef(pizzaId, it) }.let { pizza ->
            pizzaIngredientRelationDao.savePizzaWithIngredientsRelation(pizza)
        }
    }
}