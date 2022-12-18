package com.example.pizzalab.di

import android.content.Context
import com.example.pizzalab.data.local.dao.UserDao
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
}