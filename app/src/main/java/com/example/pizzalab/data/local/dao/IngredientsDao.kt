package com.example.pizzalab.data.local.dao

import androidx.room.*
import com.example.pizzalab.data.local.entity.Ingredient
import com.example.pizzalab.data.local.entity.PizzaIngredientCrossRef
import com.example.pizzalab.data.local.entity.PizzaWithIngredients
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredients: List<Ingredient>)
}