package com.example.pizzalab.data.local.dao

import androidx.room.*
import com.example.pizzalab.data.local.entity.Pizza
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePizza(pizza: Pizza)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePizzas(pizzas: List<Pizza>)

    @Query("DELETE FROM pizza_table WHERE pizza_id == :pizzaId")
    suspend fun removePizzaFromBag(pizzaId: String)

    @Transaction
    @Query("SELECT * FROM pizza_table")
    fun getPizzasInTheBag(): Flow<List<Pizza>>
}