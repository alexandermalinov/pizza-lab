package com.example.pizzalab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pizzalab.data.local.model.Pizza

@Entity(tableName = "order")
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "order_id")
    val id: String
)