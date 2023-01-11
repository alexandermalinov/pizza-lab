package com.example.pizzalab.ui.pizzadetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.pizzalab.data.repository.pizza.PizzaRepository
import com.example.pizzalab.navigation.PopBackStack
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.ui.ingredient.IngredientPresenter
import com.example.pizzalab.utils.common.ARG_PIZZA
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.vo.createpizza.PizzaUiModel
import com.example.pizzalab.vo.createpizza.toListOfIngredients
import com.example.pizzalab.vo.home.PizzaItemUiModel
import com.example.pizzalab.vo.pizzadetails.PizzaDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PizzaDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pizzaRepository: PizzaRepository
) : BaseViewModel(),
    PizzaDetailsPresenter, IngredientPresenter {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    val uiState: LiveData<PizzaDetailsUiModel>
        get() = _uiState

    private val _uiState = MutableLiveData<PizzaDetailsUiModel>()
    private val currentPizza = savedStateHandle.get<PizzaItemUiModel>(ARG_PIZZA)

    init {
        _uiState.value = PizzaDetailsUiModel(
            pizza = PizzaUiModel(
                currentPizza?.id ?: EMPTY,
                currentPizza?.price ?: EMPTY,
                currentPizza?.size ?: EMPTY,
                currentPizza?.ingredients ?: emptyList(),
                currentPizza?.title ?: EMPTY,
                currentPizza?.description ?: EMPTY
            )
        )
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun navigateBackClick() {
        _navigationLiveData.value = PopBackStack
    }

    override fun onAddToCartClick() {
        viewModelScope.launch {
            _uiState.value?.apply {
                pizzaRepository.savePizza(
                    id = UUID.randomUUID().toString(),
                    title = pizza.title,
                    description = pizza.description,
                    price = pizza.price,
                    size = pizza.size,
                    ingredientsIds = pizza.ingredients.toListOfIngredients(),
                    quantity = pizza.quantity
                )
            }
            _navigationLiveData.value = PopBackStack
        }
    }

    override fun onSizeClick(selectedSize: String) {
        _uiState.value?.pizza?.let {
            _uiState.value = _uiState.value?.copy(
                pizza = it.copy(size = selectedSize)
            )
        }
    }

    override fun onMinusClick() {
        _uiState.value?.apply {
            _uiState.value =
                copy(pizza = pizza.copy(quantity = pizza.quantity.toInt().minus(1).toString()))
        }
    }

    override fun onPlusClick() {
        _uiState.value?.apply {
            _uiState.value = copy(pizza = pizza.copy(quantity = pizza.quantity.toInt().plus(1).toString()))
        }
    }
}