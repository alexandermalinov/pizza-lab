package com.example.pizzalab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pizzalab.data.local.entity.OrderPizzaCrossRef

@Dao
interface OrderPizzasRelationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrderWithPizzaRelation(relations: List<OrderPizzaCrossRef>)

    @Query("DELETE FROM order_pizza_relation_table WHERE order_id == :orderId")
    suspend fun removeOrderWithPizzas(orderId: String)
}