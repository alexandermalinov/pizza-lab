package com.example.pizzalab.ui.createpizza

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pizzalab.R
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.ui.ingredient.IngredientPresenter
import com.example.pizzalab.ui.ingredient.IngredientType
import com.example.pizzalab.vo.createpizza.CreatePizzaUiModel
import com.example.pizzalab.vo.createpizza.IngredientUiModel
import com.example.pizzalab.vo.createpizza.PizzaUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePizzaViewModel @Inject constructor() : BaseViewModel(), CreatePizzaPresenter,
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
            IngredientUiModel(R.drawable.bacon, "Bacon", type = IngredientType.MEAT),
            IngredientUiModel(R.drawable.peperoni, "Peperoni", type = IngredientType.MEAT),
            IngredientUiModel(R.drawable.chorizo, "Chorizo", type = IngredientType.MEAT),
            IngredientUiModel(R.drawable.ham, "Ham", type = IngredientType.MEAT),
            IngredientUiModel(R.drawable.tuna_fish, "Tuna Fish", type = IngredientType.MEAT),
            IngredientUiModel(R.drawable.chicken, "Chicken", type = IngredientType.MEAT)
        )
        val vegetables = listOf(
            IngredientUiModel(R.drawable.ic_tomato, "Tomato", type = IngredientType.VEGETABLE),
            IngredientUiModel(
                R.drawable.ic_pepper,
                "Green Pepper",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(
                R.drawable.ic_mushrooms,
                "Mushrooms",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(R.drawable.ic_jalapeno, "Jalapeno", type = IngredientType.VEGETABLE),
            IngredientUiModel(R.drawable.ic_olives, "Olives", type = IngredientType.VEGETABLE),
            IngredientUiModel(R.drawable.ic_onion, "Onion", type = IngredientType.VEGETABLE),
            IngredientUiModel(R.drawable.ic_pickles, "Pickles", type = IngredientType.VEGETABLE),
            IngredientUiModel(
                R.drawable.ic_pineapple,
                "Pineapple",
                type = IngredientType.VEGETABLE
            ),
            IngredientUiModel(R.drawable.ic_corn, "Corn", type = IngredientType.VEGETABLE)
        )
        val cheeses = listOf(
            IngredientUiModel(R.drawable.ic_cheese, "Mozzarella", type = IngredientType.CHEESE),
            IngredientUiModel(R.drawable.ic_cheese, "Cheddar", type = IngredientType.CHEESE),
            IngredientUiModel(R.drawable.ic_cheese, "Parmesan", type = IngredientType.CHEESE),
            IngredientUiModel(R.drawable.ic_cheese, "Melted Cheese", type = IngredientType.CHEESE),
            IngredientUiModel(R.drawable.ic_cheese, "Emmental", type = IngredientType.CHEESE)
        )
        val sauces = listOf(
            IngredientUiModel(R.drawable.ic_sauce, "Barbeque Sauce", type = IngredientType.SAUCE),
            IngredientUiModel(R.drawable.ic_sauce, "Tomato Sauce", type = IngredientType.SAUCE),
            IngredientUiModel(R.drawable.ic_sauce, "Cream", type = IngredientType.SAUCE)
        )
        val spices = listOf(
            IngredientUiModel(R.drawable.ic_basil, "Basil", type = IngredientType.SPICE),
            IngredientUiModel(R.drawable.oregano, "Basil", type = IngredientType.SPICE)
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

    override fun onSizeClick(isChecked: Boolean, size: String) {
        if (isChecked) {
            _pizzaUiState.value = _pizzaUiState.value?.copy(size = size)
            addOrRemovePrice(true, SIZE_PRICE)
        } else {
            addOrRemovePrice(false, SIZE_PRICE)
        }
    }

    override fun onPurchaseClick() {

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
            }
        }
        return ingredients ?: emptyList()
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