package com.example.pizzalab.data.local.dao

import androidx.room.*
import com.example.pizzalab.data.local.entity.PizzaIngredientCrossRef
import com.example.pizzalab.data.local.entity.PizzaWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaIngredientRelationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePizzaWithIngredientsRelation(relations: List<PizzaIngredientCrossRef>)

    @Query("DELETE FROM pizza_ingredient_relation_table WHERE pizza_id == :pizzaId")
    suspend fun removePizzaWithIngredientFromBag(pizzaId: String)

    @Transaction
    @Query("SELECT * FROM pizza_table WHERE pizza_id = :id")
    fun getPizzaWithIngredients(id: String): Flow<PizzaWithIngredients>
}