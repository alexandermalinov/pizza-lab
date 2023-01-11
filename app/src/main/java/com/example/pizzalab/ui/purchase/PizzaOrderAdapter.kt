package com.example.pizzalab.ui.purchase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.pizzalab.R
import com.example.pizzalab.databinding.ItemPizzaOrderBinding
import com.example.pizzalab.utils.DataBoundListAdapter
import com.example.pizzalab.vo.createpizza.PizzaUiModel

class PizzaOrderAdapter(val presenter: PizzaOrderPresenter) :
    DataBoundListAdapter<PizzaUiModel, ItemPizzaOrderBinding>(
        object : DiffUtil.ItemCallback<PizzaUiModel>() {

            override fun areItemsTheSame(
                oldItem: PizzaUiModel,
                newItem: PizzaUiModel
            ) = oldItem.id === newItem.id

            override fun areContentsTheSame(
                oldItem: PizzaUiModel,
                newItem: PizzaUiModel
            ) = oldItem == newItem
        }
    ) {

    /* --------------------------------------------------------------------------------------------
     * Override
     ---------------------------------------------------------------------------------------------*/
    override fun createBinding(
        parent: ViewGroup,
        viewType: Int
    ): ItemPizzaOrderBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pizza_order,
            parent,
            false
        )

    override fun bind(
        binding: ItemPizzaOrderBinding,
        item: PizzaUiModel
    ) {
        binding.model = item
        binding.presenter = presenter
    }
}