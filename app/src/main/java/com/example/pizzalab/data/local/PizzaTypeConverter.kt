package com.example.pizzalab.data.local

import androidx.room.TypeConverter
import com.example.pizzalab.data.local.entity.Ingredient
import com.example.pizzalab.data.local.entity.Pizza
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PizzaTypeConverter {

    @TypeConverter
    fun List<Ingredient>.listOfIngredientToJson() = Json.encodeToString(this)

    @TypeConverter
    fun String.jsonToListOfIngredient() = Json.decodeFromString<List<Ingredient>>(this)

    @TypeConverter
    fun List<Pizza>.listOfPizzaToJson() = Json.encodeToString(this)

    @TypeConverter
    fun String.jsonToListOfPizza() = Json.decodeFromString<List<Pizza>>(this)
}