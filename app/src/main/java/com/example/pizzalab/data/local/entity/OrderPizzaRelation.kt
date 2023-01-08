package com.example.pizzalab.data.local.entity

import androidx.room.*

@Entity(
    tableName = "order_pizza_relation_table",
    primaryKeys = ["order_id", "pizza_id"]
)
data class OrderPizzaCrossRef(
    @ColumnInfo(name = "order_id")
    val orderId: Long,
    @ColumnInfo(name = "pizza_id")
    val pizzaId: String
)

data class OrderWithPizzas(
    @Embedded
    val order: Order,
    @Relation(
        parentColumn = "order_id",
        entity = Pizza::class,
        entityColumn = "pizza_id",
        associateBy = Junction(
            OrderPizzaCrossRef::class
        )
    )
    val pizzas: List<Pizza>
)