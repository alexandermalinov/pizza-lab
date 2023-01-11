package com.example.pizzalab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.vo.createpizza.PizzaUiModel

@kotlinx.serialization.Serializable
@Entity(tableName = "pizza_table")
data class Pizza(
    @PrimaryKey
    @ColumnInfo(name = "pizza_id")
    val id: String = EMPTY,
    @ColumnInfo(name = "price")
    var price: String = EMPTY,
    @ColumnInfo(name = "size")
    val size: String = EMPTY,
    @ColumnInfo(name = "ingredients")
    val ingredients: List<Ingredient> = listOf(),
    @ColumnInfo(name = "title")
    val title: String = EMPTY,
    @ColumnInfo(name = "description")
    val description: String = EMPTY
)

fun List<Pizza>.toListOfPizzaUiModels() = map { pizza ->
    val ingredients = pizza.ingredients.toListOfIngredientUiModel()
    PizzaUiModel(
        id = pizza.id,
        price = pizza.price,
        size = pizza.size,
        ingredients = ingredients,
        title = pizza.title.ifBlank { "Your Own Made" },
        description = pizza.description.ifBlank { ingredients.joinToString(" • ") { it.name } }
    )
}