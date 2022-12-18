package com.example.pizzalab.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pizzalab.utils.SingleLiveEvent
import com.example.pizzalab.navigation.Destination

open class BaseViewModel : ViewModel() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    val navigationLiveData: LiveData<Destination>
        get() = _navigationLiveData

    protected val _navigationLiveData = SingleLiveEvent<Destination>()
}