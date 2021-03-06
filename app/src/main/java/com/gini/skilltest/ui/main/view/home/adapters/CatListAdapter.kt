package com.gini.skilltest.ui.main.view.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gini.skilltest.R
import com.gini.skilltest.data.model.Cat
import com.gini.skilltest.ui.main.view.home.deligates.CatImageClickDelegate
import com.gini.skilltest.ui.main.view.home.viewholders.CatItemViewHolder

class CatListAdapter(
    val listener: CatImageClickDelegate
) : PagingDataAdapter<Cat, CatItemViewHolder>(CatComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatItemViewHolder =
        CatItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false), listener
        )


    override fun onBindViewHolder(holder: CatItemViewHolder, position: Int) {
        var element = getItem(position)
        holder.bind(element)
    }

    companion object {
        private val CatComparator = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return (oldItem is Cat && newItem is Cat &&
                        oldItem.id == newItem.id) ||
                        (oldItem.url == newItem.url)
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem == newItem
        }
    }
}