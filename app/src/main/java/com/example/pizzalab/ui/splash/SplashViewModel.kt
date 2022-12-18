package com.example.pizzalab.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    init {
        viewModelScope.launch {
            delay(2 * 1000)
            navigateToLogin()
        }
    }

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun navigateToLogin() {
        _navigationLiveData.value = NavigationGraph(R.id.action_splashFragment_to_registerFragment)
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
}