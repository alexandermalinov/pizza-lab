package com.example.pizzalab.ui.main

import android.view.View
import androidx.core.view.marginEnd
import androidx.navigation.NavController
import com.example.pizzalab.R
import com.example.pizzalab.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel() {

    /* --------------------------------------------------------------------------------------------
     * Exposed
    ---------------------------------------------------------------------------------------------*/
    fun setBottomNavigationVisibility(
        navController: NavController,
        view: View
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.profileFragment,
                R.id.createPizzaFragment,
                R.id.orderFragment -> view.makeVisible()
                else -> view.visibility = View.GONE
            }
        }
    }

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun View.makeVisible() {
        visibility = View.VISIBLE
    }
}