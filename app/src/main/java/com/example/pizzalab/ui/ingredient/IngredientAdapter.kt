package com.example.pizzalab.ui.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.pizzalab.R
import com.example.pizzalab.databinding.ItemPizzaIngredientBinding
import com.example.pizzalab.utils.DataBoundListAdapter
import com.example.pizzalab.vo.createpizza.IngredientUiModel

class IngredientAdapter(private val presenter: IngredientPresenter) :
    DataBoundListAdapter<IngredientUiModel, ItemPizzaIngredientBinding>(
        object : DiffUtil.ItemCallback<IngredientUiModel>() {

            override fun areItemsTheSame(
                oldItem: IngredientUiModel,
                newItem: IngredientUiModel
            ) = oldItem === newItem

            override fun areContentsTheSame(
                oldItem: IngredientUiModel,
                newItem: IngredientUiModel
            ) = oldItem == newItem
        }
    ) {

    /* --------------------------------------------------------------------------------------------
     * Override
     ---------------------------------------------------------------------------------------------*/
    override fun createBinding(
        parent: ViewGroup,
        viewType: Int
    ): ItemPizzaIngredientBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pizza_ingredient,
            parent,
            false
        )

    override fun bind(
        binding: ItemPizzaIngredientBinding,
        item: IngredientUiModel
    ) {
        binding.model = item
        binding.presenter = presenter
    }
}