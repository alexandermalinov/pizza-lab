package com.example.pizzalab.di

import android.app.Application
import androidx.room.Room
import com.example.pizzalab.data.local.RoomDatabase
import com.example.pizzalab.data.local.dao.UserDao
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
}