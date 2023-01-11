package com.example.pizzalab.ui.purchase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pizzalab.R
import com.example.pizzalab.databinding.FragmentPurchaseBinding
import com.example.pizzalab.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PurchaseFragment : BaseFragment<FragmentPurchaseBinding>() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    private val viewModel: PurchaseViewModel by viewModels()

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPizzaRecyclerView()
        observeLiveData()
    }

    override fun getLayoutId(): Int = R.layout.fragment_purchase

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun initPizzaRecyclerView() {
        dataBinding.recyclerViewPizzas.apply {
            adapter = PizzaOrderAdapter(viewModel)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeLiveData() {
        dataBinding.presenter = viewModel
        observeNavigation(viewModel.navigationLiveData)
        observeUiLiveData()
    }

    private fun observeUiLiveData() {
        viewModel.pizzasInBag.observe(viewLifecycleOwner) { uiModel ->
            dataBinding.model = uiModel
            (dataBinding.recyclerViewPizzas.adapter as PizzaOrderAdapter)
                .submitList(uiModel.pizzas)
        }
    }
}