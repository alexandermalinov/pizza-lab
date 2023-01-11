package com.example.pizzalab.ui.order_successful

import com.example.pizzalab.R
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderSuccessfulViewModel @Inject constructor() : BaseViewModel(), OrderSuccessfulPresenter {

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun backToHome() {
        _navigationLiveData.value =
            NavigationGraph(R.id.action_orderSuccessfulFragment_to_homeFragment)
    }
}