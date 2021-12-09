package com.example.practiceapp.UI

import androidx.recyclerview.widget.DiffUtil
import com.example.practiceapp.Data.ImageEntity

class ImageDiff(private val oldList : List<ImageEntity>, private val newList : List<ImageEntity>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areItemsTheSame(oldItemPosition,newItemPosition)
    }
}