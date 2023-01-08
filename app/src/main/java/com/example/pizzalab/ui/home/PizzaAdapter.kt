package com.example.pizzalab.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.pizzalab.R
import com.example.pizzalab.databinding.ItemPizzaBinding
import com.example.pizzalab.utils.DataBoundListAdapter
import com.example.pizzalab.vo.home.PizzaItemUiModel

class PizzaAdapter : DataBoundListAdapter<PizzaItemUiModel, ItemPizzaBinding>(
    object : DiffUtil.ItemCallback<PizzaItemUiModel>() {

        override fun areItemsTheSame(
            oldItem: PizzaItemUiModel,
            newItem: PizzaItemUiModel
        ) = oldItem === newItem

        override fun areContentsTheSame(
            oldItem: PizzaItemUiModel,
            newItem: PizzaItemUiModel
        ) = oldItem == newItem
    }
) {

    /* --------------------------------------------------------------------------------------------
     * Override
     ---------------------------------------------------------------------------------------------*/
    override fun createBinding(
        parent: ViewGroup,
        viewType: Int
    ): ItemPizzaBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pizza,
            parent,
            false
        )

    override fun bind(
        binding: ItemPizzaBinding,
        item: PizzaItemUiModel
    ) {
        binding.model = item
    }
}