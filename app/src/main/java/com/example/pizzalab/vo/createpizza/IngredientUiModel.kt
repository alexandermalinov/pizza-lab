package com.example.pizzalab.vo.createpizza

import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.pizzalab.BR
import com.example.pizzalab.data.local.entity.Ingredient
import com.example.pizzalab.ui.ingredient.IngredientType
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.utils.common.ZERO
import java.util.UUID

data class IngredientUiModel(
    val id: String = EMPTY,
    @DrawableRes
    val image: Int = ZERO,
    val name: String = EMPTY,
    val type: IngredientType = IngredientType.MEAT
) : BaseObservable() {

    @get:Bindable
    var isSelected: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.selected)
        }
}

fun List<IngredientUiModel>.toIngredients() = map {
    Ingredient(
        id = UUID.randomUUID().toString(),
        image = it.image,
        name = it.name,
        type = it.type.name
    )
}
