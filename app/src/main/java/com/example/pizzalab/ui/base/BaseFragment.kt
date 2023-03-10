package com.example.pizzalab.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.pizzalab.navigation.Destination
import com.example.pizzalab.navigation.navigate

abstract class BaseFragment<T: ViewDataBinding> : Fragment() {

    /* --------------------------------------------------------------------------------------------
     * Properties
    ---------------------------------------------------------------------------------------------*/
    protected lateinit var dataBinding: T

    /* --------------------------------------------------------------------------------------------
     * Override
    ---------------------------------------------------------------------------------------------*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        return dataBinding.root
    }

    /* --------------------------------------------------------------------------------------------
     * Exposed
    ---------------------------------------------------------------------------------------------*/
    abstract fun getLayoutId(): Int

    /* --------------------------------------------------------------------------------------------
     * Protected
    ---------------------------------------------------------------------------------------------*/
    protected fun observeNavigation(navigationLiveData: LiveData<Destination>) {
        navigationLiveData.observe(viewLifecycleOwner) { destination ->
            navigate(destination)
        }
    }
}