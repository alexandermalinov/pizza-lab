package com.example.pizzalab.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pizzalab.data.local.entity.OrderPizzaCrossRef

@Dao
interface OrderPizzasRelationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRelation(relation: OrderPizzaCrossRef)
}