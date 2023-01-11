package com.example.pizzalab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pizzalab.utils.common.EMPTY

@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "order_id")
    val id: String = EMPTY,
    @ColumnInfo(name = "price")
    val price: String = EMPTY,
    @ColumnInfo(name = "pizzas")
    val pizzas: List<Pizza> = listOf()
)