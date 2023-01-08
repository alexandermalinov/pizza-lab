package com.example.pizzalab.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.utils.common.TextRes
import com.example.pizzalab.vo.home.HomeUiModel
import com.example.pizzalab.vo.home.PizzaItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel(), HomePresenter {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    val uiState: LiveData<HomeUiModel>
        get() = _uiState

    private val _uiState = MutableLiveData(HomeUiModel())

    init {
        val listOfPizza = listOf(
            PizzaItemUiModel(
                title = "Clasico",
                description = "Mixed with cheese",
                price = TextRes("11,99", R.string.pizza_price)
            ),
            PizzaItemUiModel(
                title = "Mexico",
                description = "Mixed with chilli",
                price = TextRes("10,99", R.string.pizza_price)
            ),
            PizzaItemUiModel(
                title = "Italia",
                description = "Mixed with tomatoes",
                price = TextRes("7,99", R.string.pizza_price)
            ),
            PizzaItemUiModel(
                title = "Pepperoni",
                description = "Mixed with chorizo",
                price = TextRes("8,99", R.string.pizza_price)
            ),
            PizzaItemUiModel(
                title = "Master Burger",
                description = "Mixed with burger sauce",
                price = TextRes("6,99", R.string.pizza_price)
            )
        )

        _uiState.value = HomeUiModel(listOfPizza)
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onCreateYourOwnPizzaClick() {
        _navigationLiveData.value = NavigationGraph(R.id.action_homeFragment_to_createPizzaFragment)
    }

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
}