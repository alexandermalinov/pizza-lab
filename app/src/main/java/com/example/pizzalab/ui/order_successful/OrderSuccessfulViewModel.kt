package com.example.pizzalab.ui.order_successful

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.pizzalab.R
import com.example.pizzalab.data.repository.user.UserRepository
import com.example.pizzalab.navigation.NavigationGraph
import com.example.pizzalab.ui.base.BaseViewModel
import com.example.pizzalab.utils.common.ARG_TOTAL_PRICE
import com.example.pizzalab.utils.common.EMPTY
import com.example.pizzalab.vo.ordersuccessful.OrderSuccessfulUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderSuccessfulViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), OrderSuccessfulPresenter {

    val uiState: LiveData<OrderSuccessfulUiModel>
        get() = _uiState

    private val _uiState = MutableLiveData(OrderSuccessfulUiModel())

    init {
        viewModelScope.launch {
            val totalPrice = savedStateHandle.get<String>(ARG_TOTAL_PRICE) ?: EMPTY
            val address = userRepository.getCurrentUser()?.address ?: EMPTY
            _uiState.value = OrderSuccessfulUiModel(address = address, totalPrice = totalPrice)
        }
    }

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun backToHome() {
        _navigationLiveData.value =
            NavigationGraph(R.id.action_orderSuccessfulFragment_to_homeFragment)
    }
}