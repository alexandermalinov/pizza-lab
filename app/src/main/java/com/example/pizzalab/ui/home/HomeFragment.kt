package com.example.pizzalab.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzalab.R
import com.example.pizzalab.databinding.FragmentHomeBinding
import com.example.pizzalab.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    private val viewModel: HomeViewModel by viewModels()

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPizzaRecyclerView()
        observeLiveData()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun initPizzaRecyclerView() {
        dataBinding.recyclerViewPizzas.apply {
            adapter = PizzaAdapter(viewModel)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
            (dataBinding.recyclerViewPizzas.adapter as PizzaAdapter)
                .submitList(uiModel.pizzas)
        }
    }
}