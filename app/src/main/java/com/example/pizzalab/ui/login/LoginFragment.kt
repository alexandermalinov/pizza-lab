package com.example.pizzalab.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.pizzalab.R
import com.example.pizzalab.databinding.FragmentLoginBinding
import com.example.pizzalab.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    private val viewModel: LoginViewModel by viewModels()

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeLiveData()
        observeNavigation(viewModel.navigationLiveData)
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    /* --------------------------------------------------------------------------------------------
     * Private
    ---------------------------------------------------------------------------------------------*/
    private fun initUi() {
        dataBinding.presenter = viewModel
    }

    private fun observeLiveData() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiModel ->
            dataBinding.model = uiModel
        }
    }
}