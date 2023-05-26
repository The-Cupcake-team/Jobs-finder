package com.cupcake.jobsfinder.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.jobsfinder.BR
import com.cupcake.jobsfinder.R

abstract class BaseAdapter<T>(
    private var items: List<T>,
    private val listener: BaseActionListener,
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
    @get:LayoutRes
    abstract var layoutId: Int
    fun setData(newItems: List<T>) {
        val dataDiff =
            DiffUtil.calculateDiff(JobFinderDiffUtil(items, newItems) { oldItem, newItem ->
                areItemsEqual(oldItem, newItem)
            })
        items = newItems
        dataDiff.dispatchUpdatesTo(this)
    }

    open fun areItemsEqual(
        oldItem: T,
        newItem: T,
    ) = oldItem == newItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        when (holder) {
            is ItemViewHolder -> {
                //holder.binding.setVariable(BR.item,currentItem)
                // holder.binding.setVariable(BR.listener,listener)
            }
        }
    }

    override fun getItemCount(): Int = items.size
    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
    open class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

}

interface BaseActionListener