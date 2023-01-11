package com.example.pizzalab.data.local.dao

import androidx.room.*
import com.example.pizzalab.data.local.entity.Order
import com.example.pizzalab.data.local.entity.Pizza
import com.example.pizzalab.data.local.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrder(order: Order)

    @Query("DELETE FROM order_table WHERE order_id == :orderId")
    suspend fun removeOrder(orderId: String)

    @Transaction
    @Query("SELECT * FROM order_table")
    fun getAllOrders(): Flow<List<Order>>
}