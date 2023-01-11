package com.example.pizzalab.ui.pizzadetails

import androidx.lifecycle.*
import com.example.pizzalab.data.local.entity.toListOfIngredientUiModel
import com.example.pizzalab.data.repository.pizza.PizzaRepository
import com.example.pizzalab.navigation.PopBackStack
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.ui.ingredient.IngredientPresenter
import com.example.pizzalab.utils.common.ARG_PIZZA_ID
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.vo.createpizza.PizzaUiModel
import com.example.pizzalab.vo.pizzadetails.PizzaDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PizzaDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    pizzaRepository: PizzaRepository
) : BaseViewModel(),
    PizzaDetailsPresenter, IngredientPresenter {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    val uiState: LiveData<PizzaDetailsUiModel>
        get() = _uiState

    private val pizzaId = savedStateHandle[ARG_PIZZA_ID] ?: EMPTY
    private val _uiState = Transformations.map(
        pizzaRepository
            .getPizza("df37f4ce-9b35-4214-8fe0-9990d119da23")
            .asLiveData(viewModelScope.coroutineContext + Dispatchers.Default)
    ) {
        PizzaDetailsUiModel(
            pizza = PizzaUiModel(
                it.pizza.id,
                it.pizza.price,
                it.pizza.size,
                it.ingredients.toListOfIngredientUiModel(),
                it.pizza.title,
                it.pizza.description
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
        // TODO("Not yet implemented")
    }

    override fun onSizeClick(size: String) {
        //_uiState.value?.pizza
    }

    override fun onMinusClick() {
        // TODO("Not yet implemented")
    }

    override fun onPlusClick() {
        // TODO("Not yet implemented")
    }
}