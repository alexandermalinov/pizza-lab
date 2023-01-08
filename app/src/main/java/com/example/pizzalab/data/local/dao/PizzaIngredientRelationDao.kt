package com.example.pizzalab.data.local.dao

import androidx.room.*
import com.example.pizzalab.data.local.entity.PizzaIngredientCrossRef
import com.example.pizzalab.data.local.entity.PizzaWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaIngredientRelationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePizzaWithIngredientsRelation(relation: PizzaIngredientCrossRef)

    @Transaction
    @Query("SELECT * FROM pizza")
    fun getPizzaWithIngredients(): Flow<PizzaWithIngredients>
}