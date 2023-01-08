package com.example.pizzalab.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pizzalab.data.local.dao.OrderDao
import com.example.pizzalab.data.local.dao.PizzaDao
import com.example.pizzalab.data.local.dao.PizzaIngredientRelationDao
import com.example.pizzalab.data.local.dao.UserDao
import com.example.pizzalab.data.local.entity.Order
import com.example.pizzalab.data.local.entity.PizzaIngredientCrossRef
import com.example.pizzalab.data.local.entity.User

@Database(
    entities = [User::class, Order::class, PizzaIngredientCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getPizzaDao(): PizzaDao

    abstract fun getOrderDao(): OrderDao

    abstract fun getPizzaIngredientCrossRefDao(): PizzaIngredientRelationDao
}