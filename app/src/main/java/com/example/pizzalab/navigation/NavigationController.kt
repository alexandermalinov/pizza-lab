package com.example.pizzalab.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/* --------------------------------------------------------------------------------------------
 * Exposed
---------------------------------------------------------------------------------------------*/
fun Fragment.navigate(destination: Destination) {
    when (destination) {
        is Internal -> {
            handleInternalNavigation(destination)
        }
        is External -> {
            // Implement when necessary
        }
    }
}

/* --------------------------------------------------------------------------------------------
 * Private
---------------------------------------------------------------------------------------------*/
private fun Fragment.handleInternalNavigation(destination: Internal) {
    when (destination) {
        is NavigationGraph -> {
            findNavController().navigate(
                destination.actionId,
                destination.args,
                destination.navOptions,
                destination.extras
            )
        }
        is PopBackStack -> {
            findNavController().popBackStack()
        }
    }
}