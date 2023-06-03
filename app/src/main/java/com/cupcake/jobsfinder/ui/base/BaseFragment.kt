package com.cupcake.jobsfinder.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cupcake.jobsfinder.BR


abstract class BaseFragment<DB: ViewDataBinding, VM: ViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: Class<VM>
): Fragment() {

    abstract val LOG_TAG: String

    lateinit var viewModel: VM

    private var _binding: DB? = null
    protected val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!)[viewModelClass]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        _binding?.apply {
            //setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        return binding?.root
    }

    protected fun log(value: String) {
        Log.v(LOG_TAG, value)
    }

}