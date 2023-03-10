package com.example.pizzalab.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.pizzalab.R
import com.example.pizzalab.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var navigationController: NavController
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var badge: BadgeDrawable

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initNavController()
        initBottomNavigation()
    }

    override fun onSupportNavigateUp(): Boolean = navigationController.navigateUp()

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun initDataBinding() {
        dataBinding = DataBindingUtil.setContentView(
            this@MainActivity,
            R.layout.activity_main
        )
    }

    private fun initNavController() {
        navigationController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
    }

    private fun initBottomNavigation() {
        dataBinding
            .bottomNavigationMenu
            .setupWithNavController(navigationController)

        viewModel.setBottomNavigationVisibility(
            navigationController,
            dataBinding.bottomNavigationMenu
        )

        initBadgeIcon()

        viewModel.pizzasInBag.observe(this) { numberOfPizzas ->
            badge.number = numberOfPizzas
        }
    }

    private fun initBadgeIcon() {
        badge = dataBinding.bottomNavigationMenu.getOrCreateBadge(R.id.purchaseFragment)
        badge.number = 0
        badge.isVisible = true
    }
}