package com.example.pizzalab.data.repository.order

import com.example.pizzalab.data.local.entity.Order
import com.example.pizzalab.data.local.entity.Pizza
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepository @Inject constructor(private val local: OrderLocalSource) {

    /* --------------------------------------------------------------------------------------------
     * Sources
     ---------------------------------------------------------------------------------------------*/
    interface LocalSource {

        fun getAllOrders(): Flow<List<Order>>

        suspend fun saveOrder(
            id: String,
            price: String,
            pizzas: List<Pizza>
        )

        suspend fun removeOrder(orderId: String)
    }

    fun getAllOrders(): Flow<List<Order>> = local.getAllOrders()

    suspend fun saveOrder(
        id: String,
        price: String,
        pizzas: List<Pizza>
    ) {
        local.saveOrder(id, price, pizzas)
    }

    suspend fun removeOrder(orderId: String) {
        local.removeOrder(orderId)
    }
}