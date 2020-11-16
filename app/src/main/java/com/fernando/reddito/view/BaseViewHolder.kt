package com.fernando.reddito.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var currentPosition: Int = 0

    abstract fun clear()

    abstract fun onBind(position: Int)
}