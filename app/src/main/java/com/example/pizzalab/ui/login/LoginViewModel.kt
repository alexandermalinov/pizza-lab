package com.example.pizzalab.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.data.repository.user.UserRepository
import com.example.pizzalab.domain.register.ValidateEmailUseCase
import com.example.pizzalab.domain.register.ValidationStates
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.utils.common.INVALID_RES
import com.example.pizzalab.vo.login.LoginUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmailUseCase: ValidateEmailUseCase
) : BaseViewModel(), LoginPresenter {

    val uiState: LiveData<LoginUiModel>
        get() = _uiState

    private val _uiState = MutableLiveData(LoginUiModel())

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun onInvalidPassword() {
        _uiState.value?.passwordError = R.string.wrong_password
        _uiState.value?.passwordErrorEnabled = true
    }

    private fun validateEmail() {
        _uiState.value?.apply {
            when (validateEmailUseCase.invoke(email)) {
                ValidationStates.VALID -> {
                    emailErrorEnabled = false
                    emailError = INVALID_RES
                    emailEndIcon = R.drawable.ic_checkbox
                }
                ValidationStates.INVALID -> {
                    emailErrorEnabled = true
                    emailError = R.string.invalid_email
                    emailEndIcon = null
                }
                ValidationStates.EMPTY -> {
                    emailErrorEnabled = false
                    emailError = INVALID_RES
                    emailEndIcon = null
                }
            }
        }
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onContinueClick() {
        viewModelScope.launch {
            _uiState.value?.apply {
                isLoading = true
                userRepository.loginUser(email, password) { either ->
                    either.foldSuspend({ error ->
                        Timber.e("Error while logging user. e: ${error.errorMessage}")
                        validateEmail()
                        onInvalidPassword()
                    }, {
                        userRepository.setIsSignedIn(true)
                        isLoading = false
                        _navigationLiveData.value =
                            NavigationGraph(R.id.action_loginFragment_to_homeFragment)
                    })
                }
            }
        }
    }

    override fun onRegisterClick() {
        _navigationLiveData.value = NavigationGraph(R.id.action_loginFragment_to_registerFragment)
    }
}