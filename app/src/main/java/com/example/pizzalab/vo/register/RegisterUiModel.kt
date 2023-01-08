package com.example.pizzalab.vo.register

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.pizzalab.BR
import com.example.pizzalab.data.local.entity.User
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.INVALID_RES
import java.util.UUID

data class RegisterUiModel(
    val id: String = UUID.randomUUID().toString(),
    var email: String = EMPTY, // two way data binding
    var password: String = EMPTY, // two way data binding
    var confirmPassword: String = EMPTY, // two way data binding
    var isLoading: Boolean = false // two way data binding
) : BaseObservable() {

    @get:Bindable
    var emailErrorEnabled: Boolean = false
        set(value) {
            field = value
            setContinueEnabled()
            notifyPropertyChanged(BR.emailErrorEnabled)
        }

    @get:Bindable
    var emailError: Int = INVALID_RES
        set(value) {
            field = value
            notifyPropertyChanged(BR.emailError)
        }

    @get:Bindable
    var emailEndIcon: Int? = INVALID_RES
        set(value) {
            field = value
            notifyPropertyChanged(BR.emailEndIcon)
        }

    @get:Bindable
    var passwordError: Int = INVALID_RES
        set(value) {
            field = value
            notifyPropertyChanged(BR.passwordError)
        }

    @get:Bindable
    var passwordErrorEnabled: Boolean = false
        set(value) {
            field = value
            setContinueEnabled()
            notifyPropertyChanged(BR.passwordErrorEnabled)
        }

    @get:Bindable
    var confirmPasswordError: Int = INVALID_RES
        set(value) {
            field = value
            notifyPropertyChanged(BR.confirmPasswordError)
        }

    @get:Bindable
    var confirmPasswordErrorEnabled: Boolean = false
        set(value) {
            field = value
            setContinueEnabled()
            notifyPropertyChanged(BR.confirmPasswordErrorEnabled)
        }

    @get:Bindable
    var isContinueEnabled: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.continueEnabled)
        }

    private fun setContinueEnabled() {
        isContinueEnabled = emailErrorEnabled.not() &&
                passwordErrorEnabled.not() &&
                confirmPasswordErrorEnabled.not() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                confirmPassword.isNotBlank()
    }
}

fun RegisterUiModel.toUser() = User(id, email, password)
