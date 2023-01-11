package com.example.pizzalab.data.local.entity

import androidx.room.*

@Entity(
    tableName = "pizza_ingredient_relation_table",
    primaryKeys = ["pizza_id", "ingredient_id"]
)
data class PizzaIngredientCrossRef(
    @ColumnInfo(name = "pizza_id")
    val pizzaId: String,
    @ColumnInfo(name = "ingredient_id")
    val ingredientId: String
)

data class PizzaWithIngredients(
    @Embedded
    val pizza: Pizza,
    @Relation(
        parentColumn = "pizza_id",
        entity = Ingredient::class,
        entityColumn = "ingredient_id",
        associateBy = Junction(PizzaIngredientCrossRef::class)
    )
    val ingredients: List<Ingredient>
)