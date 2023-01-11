package com.example.pizzalab.vo.purchase

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.pizzalab.BR
import com.example.pizzalab.utils.common.ZERO
import com.example.pizzalab.vo.createpizza.PizzaUiModel
import java.util.UUID

data class PurchaseUiModel(
    val id: String = UUID.randomUUID().toString(),
    val discountAmount: String = ZERO.toString(),
    val totalPrice: String = ZERO.toString()
) : BaseObservable() {

    @get:Bindable
    var pizzas: List<PizzaUiModel> = listOf()
        set(value) {
            field = value
            notifyPropertyChanged(BR.pizzas)
        }
}
