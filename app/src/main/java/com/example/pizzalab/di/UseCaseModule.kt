package com.example.pizzalab.di

import com.example.pizzalab.domain.register.ValidateEmailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideValidateEmailUserCase(): ValidateEmailUseCase = ValidateEmailUseCase()
}
