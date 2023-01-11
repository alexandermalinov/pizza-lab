package com.example.pizzalab.ui.base

import androidx.lifecycle.viewModelScope
import com.example.pizzalab.domain.register.ValidationStates
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseInputChangeViewModel : BaseViewModel() {

    /* --------------------------------------------------------------------------------------------
     * Protected
    ---------------------------------------------------------------------------------------------*/
    protected var lastText = ValidationStates.EMPTY

    @OptIn(FlowPreview::class)
    fun onTextChanged(
        searchFlow: Flow<CharSequence>,
        delay: Long = 1000,
        fetchData: suspend (CharSequence) -> Unit
    ) {
        searchFlow
            .debounce(delay)
            .onEach { fetchData(it) }
            .launchIn(viewModelScope)
    }
}