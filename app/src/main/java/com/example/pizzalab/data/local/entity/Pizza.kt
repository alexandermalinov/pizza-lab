package com.example.pizzalab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizza")
data class Pizza(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: String
)