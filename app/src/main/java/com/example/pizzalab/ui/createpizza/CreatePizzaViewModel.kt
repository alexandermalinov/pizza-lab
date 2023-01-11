package com.example.pizzalab.ui.createpizza

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.data.repository.pizza.PizzaRepository
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.ui.ingredient.IngredientPresenter
import com.example.pizzalab.ui.ingredient.IngredientType
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.vo.createpizza.CreatePizzaUiModel
import com.example.pizzalab.vo.createpizza.IngredientUiModel
import com.example.pizzalab.vo.createpizza.PizzaUiModel
import com.example.pizzalab.vo.createpizza.toListOfIngredients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreatePizzaViewModel @Inject constructor(
    private val pizzaRepository: PizzaRepository
) : BaseViewModel(), CreatePizzaPresenter,
    IngredientPresenter {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    val uiState: LiveData<CreatePizzaUiModel>
        get() = _uiState

    val pizzaUiState: LiveData<PizzaUiModel>
        get() = _pizzaUiState

    private val _uiState = MutableLiveData(CreatePizzaUiModel())
    private val _pizzaUiState = MutableLiveData(PizzaUiModel())

    init {
        val meats = listOf(
            IngredientUiModel(image = R.drawable.bacon, name = "Bacon", type = IngredientType.MEAT),
            IngredientUiModel(
                image = R.drawable.peperoni,
                name = "Peperoni",
                type = IngredientType.MEAT
            ),
            IngredientUiModel(
                image = R.drawable.chorizo,
                name = "Chorizo",
                type = IngredientType.MEAT
            ),
            IngredientUiModel(image = R.drawable.ham, name = "Ham", type = IngredientType.MEAT),
            IngredientUiModel(
                image = R.drawable.tuna_fish,
                name = "Tuna Fish",
                type = IngredientType.MEAT
            ),
            IngredientUiModel(
                image = R.drawable.chicken,
                name = "Chicken",
                type = IngredientType.MEAT
            )
        )
        val vegetables = listOf(
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
                image = R.drawable.ic_jalapeno,
                name = "Jalapeno",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                image = R.drawable.ic_olives,
                name = "Olives",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                image = R.drawable.ic_onion,
                name = "Onion",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                image = R.drawable.ic_pickles,
                name = "Pickles",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                image = R.drawable.ic_pineapple,
                name = "Pineapple",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                image = R.drawable.ic_corn,
                name = "Corn",
                type = IngredientType.VEGETABLE
            )
        )
        val cheeses = listOf(
            IngredientUiModel(
                image = R.drawable.ic_cheese,
                name = "Mozzarella",
                type = IngredientType.CHEESE
            ),
            IngredientUiModel(
                image = R.drawable.ic_cheese,
                name = "Cheddar",
                type = IngredientType.CHEESE
            ),
            IngredientUiModel(
                image = R.drawable.ic_cheese,
                name = "Parmesan",
                type = IngredientType.CHEESE
            ),
            IngredientUiModel(
                image = R.drawable.ic_cheese,
                name = "Melted Cheese",
                type = IngredientType.CHEESE
            ),
            IngredientUiModel(
                image = R.drawable.ic_cheese,
                name = "Emmental",
                type = IngredientType.CHEESE
            )
        )
        val sauces = listOf(
            IngredientUiModel(
                image = R.drawable.ic_sauce,
                name = "Barbeque Sauce",
                type = IngredientType.SAUCE
            ),
            IngredientUiModel(
                image = R.drawable.ic_sauce,
                name = "Tomato Sauce",
                type = IngredientType.SAUCE
            ),
            IngredientUiModel(
                image = R.drawable.ic_sauce,
                name = "Cream",
                type = IngredientType.SAUCE
            )
        )
        val spices = listOf(
            IngredientUiModel(
                image = R.drawable.ic_basil,
                name = "Basil",
                type = IngredientType.SPICE
            ),
            IngredientUiModel(
                image = R.drawable.oregano,
                name = "Basil",
                type = IngredientType.SPICE
            )
        )
        _uiState.value = CreatePizzaUiModel(
            meats = meats,
            vegetables = vegetables,
            cheeses = cheeses,
            spices = spices,
            sauces = sauces
        )
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onIngredientClick(ingredientName: String, type: IngredientType) {
        when (type) {
            IngredientType.MEAT -> {
                _uiState.value = _uiState.value?.copy(
                    meats = handleIngredientOnClick(ingredientName, _uiState.value?.meats)
                )
            }
            IngredientType.CHEESE -> {
                _uiState.value = _uiState.value?.copy(
                    cheeses = handleIngredientOnClick(ingredientName, _uiState.value?.cheeses)
                )
            }
            IngredientType.VEGETABLE -> {
                _uiState.value = _uiState.value?.copy(
                    vegetables = handleIngredientOnClick(ingredientName, _uiState.value?.vegetables)
                )
            }
            IngredientType.SAUCE -> {
                _uiState.value = _uiState.value?.copy(
                    sauces = handleIngredientOnClick(ingredientName, _uiState.value?.sauces)
                )
            }
            IngredientType.SPICE -> {
                _uiState.value = _uiState.value?.copy(
                    spices = handleIngredientOnClick(ingredientName, _uiState.value?.spices)
                )
            }
        }
    }

    override fun onSizeClick(size: String) {
        _pizzaUiState.value = _pizzaUiState.value?.copy(size = size)
        addOrRemovePrice(false, SIZE_PRICE)
        addOrRemovePrice(true, SIZE_PRICE)
    }

    override fun onPurchaseClick() {
        viewModelScope.launch {
            _pizzaUiState.value?.apply {
                pizzaRepository.savePizza(
                    id = UUID.randomUUID().toString(),
                    title = EMPTY,
                    description = EMPTY,
                    price = price,
                    size = size,
                    ingredientsIds = ingredients.toListOfIngredients(),
                    quantity = quantity
                )
            }
            _navigationLiveData.value =
                NavigationGraph(R.id.action_createPizzaFragment_to_homeFragment)
        }
    }

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun handleIngredientOnClick(
        name: String,
        ingredients: List<IngredientUiModel>?
    ): List<IngredientUiModel> {
        ingredients?.forEach { ingredient ->
            if (ingredient.name == name) {
                ingredient.isSelected = ingredient.isSelected.not()
                addOrRemovePrice(ingredient.isSelected, INGREDIENT_PRICE)
                addOrRemoveIngredient(ingredient)
            }
        }
        return ingredients ?: emptyList()
    }

    private fun addOrRemoveIngredient(ingredient: IngredientUiModel) {
        _pizzaUiState.value?.apply {
            _pizzaUiState.value = if (ingredient.isSelected) {
                copy(ingredients = ingredients.plus(ingredient))
            } else {
                copy(ingredients = ingredients.minus(ingredient))
            }
        }
    }

    private fun addOrRemovePrice(shouldAdd: Boolean, value: Double) {
        _pizzaUiState.value?.apply {
            val priceInDoubleType = price.toDouble()
            _pizzaUiState.value = copy(
                price = if (shouldAdd)
                    priceInDoubleType.plus(value).toString()
                else
                    priceInDoubleType.minus(value).toString()
            )
        }
    }

    companion object {
        private const val INGREDIENT_PRICE = 0.50
        private const val SIZE_PRICE = 3.0
    }
}