package com.example.pizzalab.ui.order_successful

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.pizzalab.R
import com.example.pizzalab.databinding.FragmentOrderSuccessfulBinding
import com.example.pizzalab.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderSuccessfulFragment : BaseFragment<FragmentOrderSuccessfulBinding>() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    private val viewModel: OrderSuccessfulViewModel by viewModels()

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.presenter = viewModel
        observeNavigation(viewModel.navigationLiveData)
    }

    override fun getLayoutId(): Int = R.layout.fragment_order_successful
}