package com.example.pizzalab.ui.pizzadetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzalab.R
import com.example.pizzalab.databinding.FragmentPizzaDetailsBinding
import com.example.pizzalab.ui.base.BaseFragment
import com.example.pizzalab.ui.ingredient.IngredientAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PizzaDetailsFragment : BaseFragment<FragmentPizzaDetailsBinding>() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    private val viewModel: PizzaDetailsViewModel by viewModels()

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initIngredientRecyclerView()
        observeLiveData()
    }

    override fun getLayoutId(): Int = R.layout.fragment_pizza_details

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun initIngredientRecyclerView() {
        dataBinding.recyclerviewIngredients.apply {
            adapter = IngredientAdapter(viewModel)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeLiveData() {
        dataBinding.presenter = viewModel
        observeNavigation(viewModel.navigationLiveData)
        observeUiLiveData()
    }

    private fun observeUiLiveData() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            dataBinding.model = uiState
            (dataBinding.recyclerviewIngredients.adapter as IngredientAdapter)
                .submitList(uiState.pizza.ingredients)
        }
    }
}