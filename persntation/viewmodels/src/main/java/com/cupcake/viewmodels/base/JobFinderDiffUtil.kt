package com.cupcake.viewmodels.base

import androidx.recyclerview.widget.DiffUtil

class JobFinderDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>,
    val function: (T, T) -> Boolean
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int =newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return function(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}