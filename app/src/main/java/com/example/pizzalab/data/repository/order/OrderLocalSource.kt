package com.example.pizzalab.data.repository.order

import com.example.pizzalab.data.local.dao.*
import com.example.pizzalab.data.local.entity.Order
import com.example.pizzalab.data.local.entity.OrderPizzaCrossRef
import com.example.pizzalab.data.local.entity.Pizza
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderLocalSource @Inject constructor(
    private val orderPizzaRelationDao: OrderPizzasRelationDao,
    private val orderDao: OrderDao,
    private val pizzaDao: PizzaDao
) : OrderRepository.LocalSource {

    /* --------------------------------------------------------------------------------------------
     * Override
     ---------------------------------------------------------------------------------------------*/
    override fun getAllOrders(): Flow<List<Order>> = orderDao.getAllOrders()

    override suspend fun saveOrder(
        id: String,
        price: String,
        pizzas: List<Pizza>
    ) {
        pizzaDao.savePizzas(pizzas)
        saveOrderWithPizzas(id, pizzas.map { it.id })
        val order = Order(id, price, pizzas)
        orderDao.saveOrder(order)
    }

    override suspend fun removeOrder(orderId: String) {
        orderPizzaRelationDao.removeOrderWithPizzas(orderId)
        orderDao.removeOrder(orderId)
    }

    /* --------------------------------------------------------------------------------------------
     * Private
     ---------------------------------------------------------------------------------------------*/
    private suspend fun saveOrderWithPizzas(orderId: String, pizzasIds: List<String>) {
        pizzasIds.map { OrderPizzaCrossRef(orderId, it) }.let { order ->
            orderPizzaRelationDao.saveOrderWithPizzaRelation(order)
        }
    }
}