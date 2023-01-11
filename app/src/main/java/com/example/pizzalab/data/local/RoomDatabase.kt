package com.example.pizzalab.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzalab.data.local.dao.*
import com.example.pizzalab.data.local.entity.*

@Database(
    entities = [
        User::class,
        Order::class,
        Pizza::class,
        Ingredient::class,
        PizzaIngredientCrossRef::class,
        OrderPizzaCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(PizzaTypeConverter::class)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getPizzaDao(): PizzaDao

    abstract fun getOrderDao(): OrderDao

    abstract fun getPizzaIngredientCrossRefDao(): PizzaIngredientRelationDao

    abstract fun getIngredientsDao(): IngredientsDao

    abstract fun getOrderPizzasCrossRefDao(): OrderPizzasRelationDao
}