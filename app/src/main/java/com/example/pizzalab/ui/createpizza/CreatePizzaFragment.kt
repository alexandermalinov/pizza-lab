package com.example.pizzalab.ui.createpizza

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzalab.R
import com.example.pizzalab.databinding.FragmentCreatePizzaBinding
import com.example.pizzalab.ui.base.BaseFragment
import com.example.pizzalab.ui.ingredient.IngredientAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePizzaFragment : BaseFragment<FragmentCreatePizzaBinding>() {
    /* --------------------------------------------------------------------------------------------
         * Properties
        ---------------------------------------------------------------------------------------------*/
    private val viewModel: CreatePizzaViewModel by viewModels()

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPizzaRecyclerView()
        observeLiveData()
    }

    override fun getLayoutId(): Int = R.layout.fragment_create_pizza

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun initPizzaRecyclerView() {
        dataBinding.apply {
            recyclerviewMeat.adapter = IngredientAdapter(viewModel)
            recyclerviewMeat.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            recyclerviewVegetables.adapter = IngredientAdapter(viewModel)
            recyclerviewVegetables.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            recyclerviewCheese.adapter = IngredientAdapter(viewModel)
            recyclerviewCheese.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            recyclerviewSpices.adapter = IngredientAdapter(viewModel)
            recyclerviewSpices.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            recyclerviewSauce.adapter = IngredientAdapter(viewModel)
            recyclerviewSauce.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun observeLiveData() {
        dataBinding.presenter = viewModel
        observeNavigation(viewModel.navigationLiveData)
        observeUiLiveData()
    }

    private fun observeUiLiveData() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiModel ->
            dataBinding.model = uiModel
            (dataBinding.recyclerviewMeat.adapter as IngredientAdapter)
                .submitList(uiModel.meats)

            (dataBinding.recyclerviewVegetables.adapter as IngredientAdapter)
                .submitList(uiModel.vegetables)

            (dataBinding.recyclerviewCheese.adapter as IngredientAdapter)
                .submitList(uiModel.cheeses)

            (dataBinding.recyclerviewSpices.adapter as IngredientAdapter)
                .submitList(uiModel.spices)

            (dataBinding.recyclerviewSauce.adapter as IngredientAdapter)
                .submitList(uiModel.sauces)
        }

        viewModel.pizzaUiState.observe(viewLifecycleOwner) { pizza ->
            dataBinding.pizzaModel = pizza
        }
    }
}