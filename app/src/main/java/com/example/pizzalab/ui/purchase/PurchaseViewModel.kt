package com.example.pizzalab.ui.purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.data.local.entity.toListOfPizzaUiModels
import com.example.pizzalab.data.repository.order.OrderRepository
import com.example.pizzalab.data.repository.pizza.PizzaRepository
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.vo.createpizza.toListOfPizza
import com.example.pizzalab.vo.purchase.PurchaseUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val pizzaRepository: PizzaRepository,
    private val orderRepository: OrderRepository
) : BaseViewModel(), PurchasePresenter, PizzaOrderPresenter {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    val pizzasInBag: LiveData<PurchaseUiModel>
        get() = _pizzasInBag

    private val _pizzasInBag = Transformations.map(
        pizzaRepository
            .getAllPizzasInBag()
            .asLiveData(viewModelScope.coroutineContext + Dispatchers.Default)
    ) { pizzas ->
        val allPizzas = pizzas.toListOfPizzaUiModels()
        PurchaseUiModel(
            discountAmount = "0",
            totalPrice = allPizzas.sumOf { pizza -> pizza.price.toDouble() }.toString()
        ).apply {
            this.pizzas = allPizzas
        }
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun removePizza(pizzaId: String) {
        viewModelScope.launch {
            _pizzasInBag.value?.let { allPizzas ->
                allPizzas.pizzas = allPizzas.pizzas
                    .minus(allPizzas.pizzas.first { it.id == pizzaId })
            }
            pizzaRepository.removePizzaFromTheBag(pizzaId)
        }
    }

    override fun orderNow() {
        viewModelScope.launch {
            _pizzasInBag.value?.apply {
                if (pizzas.isNotEmpty()) {
                    orderRepository.saveOrder(
                        id = id,
                        price = totalPrice,
                        pizzas = pizzas.toListOfPizza()
                    )
                    pizzas.forEach { pizzaRepository.removePizzaFromTheBag(it.id) }
                    _navigationLiveData.value =
                        NavigationGraph(R.id.action_purchaseFragment_to_orderFragment)
                }
            }
        }
    }
}