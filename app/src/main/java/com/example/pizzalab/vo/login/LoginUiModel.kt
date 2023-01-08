package com.example.pizzalab.vo.login

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.pizzalab.BR
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.INVALID_RES

data class LoginUiModel(
    var email: String = EMPTY, // two way data binding
    var password: String = EMPTY, // two way data binding
    var isLoading: Boolean = false // two way data binding
) : BaseObservable() {

    @get:Bindable
    var emailErrorEnabled: Boolean = false
        set(value) {
            field = value
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
            notifyPropertyChanged(BR.passwordErrorEnabled)
        }
}