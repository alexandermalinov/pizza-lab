package com.example.pizzalab.ui.home

import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.data.repository.pizza.PizzaRepository
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.ui.ingredient.IngredientType
import com.example.pizzalab.utils.common.ARG_PIZZA
import com.example.pizzalab.vo.createpizza.IngredientUiModel
import com.example.pizzalab.vo.createpizza.toListOfIngredients
import com.example.pizzalab.vo.home.HomeUiModel
import com.example.pizzalab.vo.home.PizzaItemUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val pizzaRepository: PizzaRepository) :
    BaseViewModel(), HomePresenter, PizzaPresenter {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    val uiState: LiveData<HomeUiModel>
        get() = _uiState

    private val _uiState = MutableLiveData(HomeUiModel())

    init {
        val ingredients = listOf(
            IngredientUiModel(
                image = R.drawable.peperoni,
                name = "Peperoni",
                type = IngredientType.MEAT
            ),
            IngredientUiModel(
                image = R.drawable.ic_tomato,
                name = "Tomato",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                image = R.drawable.ic_pepper,
                name = "Green Pepper",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                image = R.drawable.ic_mushrooms,
                name = "Mushrooms",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                image = R.drawable.ic_cheese,
                name = "Parmesan",
                type = IngredientType.CHEESE
            )
        )
        val listOfPizza = listOf(
            PizzaItemUiModel(
                image = R.drawable.pizza_1,
                title = "Clasico",
                description = "Mixed with cheese",
                price = "11.99",
                ingredients = ingredients
            ),
            PizzaItemUiModel(
                image = R.drawable.pizza_1,
                title = "Mexico",
                description = "Mixed with chilli",
                price = "10.99",
                ingredients = ingredients
            ),
            PizzaItemUiModel(
                image = R.drawable.pizza_1,
                title = "Italia",
                description = "Mixed with tomatoes",
                price = "7.99",
                ingredients = ingredients
            ),
            PizzaItemUiModel(
                image = R.drawable.pizza_1,
                title = "Pepperoni",
                description = "Mixed with chorizo",
                price = "8.99",
                ingredients = ingredients
            ),
            PizzaItemUiModel(
                image = R.drawable.pizza_1,
                title = "Master Burger",
                description = "Mixed with burger sauce",
                price = "6.99",
                ingredients = ingredients
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

    override fun onAddToBag(pizzaId: String) {
        viewModelScope.launch {
            _uiState.value?.pizzas?.apply {
                val selectedPizza = first { it.id == pizzaId }
                pizzaRepository.savePizza(
                    id = UUID.randomUUID().toString(),
                    title = selectedPizza.title,
                    description = selectedPizza.description,
                    price = selectedPizza.price,
                    size = selectedPizza.size,
                    ingredientsIds = selectedPizza.ingredients.toListOfIngredients(),
                    quantity = selectedPizza.quantity
                )
            }
        }
    }

    override fun onPizzaClick(pizzaId: String) {
        _uiState.value?.pizzas
            ?.first { it.id == pizzaId }
            ?.let { selectedPizza ->
                _navigationLiveData.value = NavigationGraph(
                    R.id.action_homeFragment_to_pizzaDetailsFragment,
                    bundleOf(ARG_PIZZA to selectedPizza)
                )
            }
    }
}