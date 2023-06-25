package com.cupcake.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cupcake.ui.BR
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment<DB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes
    private val layoutId: Int,
    private val viewModelClass: Class<VM>
) : BottomSheetDialogFragment() {

    abstract val LOG_TAG: String

    lateinit var viewModel: VM

    private lateinit var _binding: DB
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
        _binding.apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    protected fun log(value: String) {
        Log.v(LOG_TAG, value)
    }
}