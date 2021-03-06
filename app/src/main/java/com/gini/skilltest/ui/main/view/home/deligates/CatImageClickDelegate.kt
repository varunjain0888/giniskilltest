package com.gini.skilltest.ui.main.view.home.deligates

import android.widget.ImageView
import com.gini.skilltest.data.model.Cat

interface CatImageClickDelegate {
    fun onClick(item: Cat?, imageView: ImageView)
}