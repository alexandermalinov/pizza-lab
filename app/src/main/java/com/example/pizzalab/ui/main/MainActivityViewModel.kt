package com.example.pizzalab.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pizzalab.R
import com.example.pizzalab.data.repository.pizza.PizzaRepository
import com.example.pizzalab.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    pizzaRepository: PizzaRepository
) : BaseViewModel() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    val pizzasInBag: LiveData<Int>
        get() = _pizzasInBag

    private val _pizzasInBag = Transformations.map(
        pizzaRepository
            .getAllPizzasInBag()
            .asLiveData(viewModelScope.coroutineContext + Dispatchers.Default)
    ) {
        it.count()
    }

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
                R.id.purchaseFragment -> view.makeVisible()
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