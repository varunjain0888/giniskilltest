package com.gini.skilltest.ui.main.view.home.viewholders

import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.gini.skilltest.R
import com.gini.skilltest.data.model.Cat
import com.gini.skilltest.databinding.ItemCatBinding
import com.gini.skilltest.ui.main.view.home.deligates.CatImageClickDelegate
import com.gini.skilltest.ui.main.view.home.deligates.ImageLoaderDelegate
import com.gini.skilltest.utils.loadImage


class CatItemViewHolder(
    itemView: View,
    val listener: CatImageClickDelegate
) : RecyclerView.ViewHolder(itemView) {
    var binding: ItemCatBinding = ItemCatBinding.bind(itemView)
    val curveRadius = 15F
    fun bind(item: Cat?) {

        ViewCompat.setTransitionName(binding.ivImage, "${item?.id}$bindingAdapterPosition")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            binding.ivImage.outlineProvider = object : ViewOutlineProvider() {

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(
                        0,
                        0,
                        view?.width ?: 0,
                        view?.height ?: 0,
                        curveRadius
                    )
                }
            }
        }

        binding.ivImage.clipToOutline = true
        binding.ivImage.loadImage(item?.url, object : ImageLoaderDelegate {
            override fun onLoadSuccess() {
            }

            override fun onLoadFailed() {
            }
        })
        binding.loader.loadImage(R.drawable.loader)
        binding.root.setOnClickListener {
            listener.onClick(item, binding.ivImage)
        }
    }
}