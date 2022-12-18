package com.example.pizzalab.ui.register

import kotlinx.coroutines.flow.Flow

interface RegisterPresenter {

    fun onContinueClick()

    fun onBackClick()

    fun onEmailTextChanged(textFlow: Flow<CharSequence>)

    fun onPasswordTextChanged(textFlow: Flow<CharSequence>)

    fun onConfirmPasswordTextChanged(textFlow: Flow<CharSequence>)
}