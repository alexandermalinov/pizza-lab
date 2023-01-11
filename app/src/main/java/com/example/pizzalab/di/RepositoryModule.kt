package com.example.pizzalab.di

import android.content.Context
import com.example.pizzalab.data.local.dao.*
import com.example.pizzalab.data.repository.order.OrderLocalSource
import com.example.pizzalab.data.repository.order.OrderRepository
import com.example.pizzalab.data.repository.pizza.PizzaLocalSource
import com.example.pizzalab.data.repository.pizza.PizzaRepository
import com.example.pizzalab.data.repository.user.UserLocalSource
import com.example.pizzalab.data.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserLocalSource(context: Context, userDao: UserDao): UserRepository.LocalSource =
        UserLocalSource(context, userDao)

    @Singleton
    @Provides
    fun providePizzaLocalSource(
        pizzaDao: PizzaDao,
        ingredientsDao: IngredientsDao,
        pizzaIngredientRelationDao: PizzaIngredientRelationDao
    ): PizzaRepository.LocalSource = PizzaLocalSource(
        pizzaIngredientRelationDao,
        pizzaDao,
        ingredientsDao
    )

    @Singleton
    @Provides
    fun provideOrderLocalSource(
        orderPizzaRelationDao: OrderPizzasRelationDao,
        orderDao: OrderDao,
        pizzaDao: PizzaDao
    ): OrderRepository.LocalSource = OrderLocalSource(
        orderPizzaRelationDao,
        orderDao,
        pizzaDao
    )
}