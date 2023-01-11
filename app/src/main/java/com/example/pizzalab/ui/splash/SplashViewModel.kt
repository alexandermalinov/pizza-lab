package com.example.pizzalab.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.data.repository.user.UserRepository
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.utils.common.SPLASH_SCREEN_DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    init {
        viewModelScope.launch {
            navigateUser()
        }
    }

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private suspend fun navigateUser() {
        userRepository.isSignedIn() { either ->
            viewModelScope.launch {
                delay(SPLASH_SCREEN_DELAY)
                either.foldSuspend({
                    navigateToLogin()
                }, {
                    navigateToHome()
                })
            }
        }
    }

    private fun navigateToHome() {
        _navigationLiveData.value = NavigationGraph(R.id.action_splashFragment_to_homeFragment)
    }

    private fun navigateToLogin() {
        _navigationLiveData.value = NavigationGraph(R.id.action_splashFragment_to_loginFragment)
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
}