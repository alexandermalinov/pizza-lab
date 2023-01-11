package com.example.pizzalab.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pizzalab.ui.ingredient.IngredientType
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.ZERO
import com.example.pizzalab.vo.createpizza.IngredientUiModel

@kotlinx.serialization.Serializable
@Entity(tableName = "ingredient_table")
data class Ingredient(
    @PrimaryKey
    @ColumnInfo(name = "ingredient_id")
    val id: String,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: String
)

fun List<Ingredient>.toListOfIngredientUiModel() = map { ingredient ->
    IngredientUiModel(
        id = ingredient.id,
        image = ingredient.image,
        name = ingredient.name,
        type = when (ingredient.type) {
            "MEAT" -> IngredientType.MEAT
            "VEGETABLE" -> IngredientType.VEGETABLE
            "CHEESE" -> IngredientType.CHEESE
            "SAUCE" -> IngredientType.SAUCE
            else -> IngredientType.SPICE
        }
    )
}
