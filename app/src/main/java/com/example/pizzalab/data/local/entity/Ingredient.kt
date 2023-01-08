package com.example.pizzalab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.pizzalab.ui.ingredient.IngredientType
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.ZERO

@Entity(tableName = "ingredient")
data class Ingredient(
    @ColumnInfo(name = "ingredient_id")
    val id: String,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: String
)
