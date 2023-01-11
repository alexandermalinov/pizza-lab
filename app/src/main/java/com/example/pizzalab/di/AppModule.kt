package com.example.pizzalab.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.pizzalab.utils.common.USER_SHARED_PREFS_KEY
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Singleton
    fun provideApplication(app: Application): Application = app

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(USER_SHARED_PREFS_KEY, Context.MODE_PRIVATE)
}