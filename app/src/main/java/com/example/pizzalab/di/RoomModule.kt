package com.example.pizzalab.di

import android.app.Application
import androidx.room.Room
import com.example.pizzalab.data.local.RoomDatabase
import com.example.pizzalab.data.local.dao.*
import com.example.pizzalab.utils.common.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): RoomDatabase =
        Room.databaseBuilder(
            application.applicationContext,
            RoomDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideUserDao(database: RoomDatabase): UserDao = database.getUserDao()

    @Singleton
    @Provides
    fun providePizzaIngredientDao(database: RoomDatabase): PizzaIngredientRelationDao =
        database.getPizzaIngredientCrossRefDao()

    @Singleton
    @Provides
    fun provideIngredientDao(database: RoomDatabase): IngredientsDao =
        database.getIngredientsDao()

    @Singleton
    @Provides
    fun providePizzaDao(database: RoomDatabase): PizzaDao =
        database.getPizzaDao()

    @Singleton
    @Provides
    fun provideOrderDao(database: RoomDatabase): OrderDao =
        database.getOrderDao()

    @Singleton
    @Provides
    fun provideOrderPizzasRelationDao(database: RoomDatabase): OrderPizzasRelationDao =
        database.getOrderPizzasCrossRefDao()
}