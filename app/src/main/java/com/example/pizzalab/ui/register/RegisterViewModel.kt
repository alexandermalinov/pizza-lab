package com.example.pizzalab.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.data.repository.user.UserRepository
import com.example.pizzalab.domain.register.ValidateEmailUseCase
import com.example.pizzalab.domain.register.ValidationStates
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.navigation.PopBackStack
import com.example.pizzalab.ui.base.BaseInputChangeViewModel
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.INVALID_RES
import com.example.pizzalab.vo.register.RegisterUiModel
import com.example.pizzalab.vo.register.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmailUseCase: ValidateEmailUseCase
) : BaseInputChangeViewModel(), RegisterPresenter {

    val uiState: LiveData<RegisterUiModel>
        get() = _uiState

    private val _uiState = MutableLiveData(RegisterUiModel())

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun setIsPasswordValid(password: String) {
        _uiState.value?.apply {
            when (password.length) {
                in 1 until PASSWORD_MIN_LENGTH -> {
                    passwordError = R.string.invalid_password
                    passwordErrorEnabled = true
                }
                else -> {
                    passwordError = INVALID_RES
                    passwordErrorEnabled = false
                }
            }
        }
    }

    private fun setIsConfirmPasswordValid(confirmPassword: String) {
        _uiState.value?.apply {
            if (confirmPassword.contentEquals(password).not()) {
                confirmPasswordError = R.string.invalid_confirm_password
                confirmPasswordErrorEnabled = true
            } else {
                confirmPasswordError = INVALID_RES
                confirmPasswordErrorEnabled = false
            }
        }
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onContinueClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value?.let {
                userRepository.registerUser(
                    RegisterUiModel(email = it.email, password = it.password).toUser()
                ) { either ->
                    either.foldSuspend({ error ->
                        Timber.e("Error registering user in local database e: ${error.errorMessage}")
                        // TODO - visualize the error
                    }, {
                        userRepository.setIsSignedIn(true)
                        _navigationLiveData.value =
                            NavigationGraph(R.id.action_registerFragment_to_homeFragment)
                    })
                }
            }
        }
    }

    override fun onBackClick() {
        _navigationLiveData.value = PopBackStack
    }

    override fun onEmailTextChanged(textFlow: Flow<CharSequence>) {
        onTextChanged(textFlow) { email ->
            _uiState.value?.apply {
                when (validateEmailUseCase.invoke(email.toString())) {
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
    }

    override fun onPasswordTextChanged(textFlow: Flow<CharSequence>) {
        onTextChanged(textFlow) { password ->
            setIsPasswordValid(password.toString())
            setIsConfirmPasswordValid(_uiState.value?.confirmPassword ?: EMPTY)
        }
    }

    override fun onConfirmPasswordTextChanged(textFlow: Flow<CharSequence>) {
        onTextChanged(textFlow) { confirmPassword ->
            setIsConfirmPasswordValid(confirmPassword.toString())
            setIsPasswordValid(_uiState.value?.password ?: EMPTY)
        }
    }

    companion object {
        const val PASSWORD_MIN_LENGTH = 8
    }
}