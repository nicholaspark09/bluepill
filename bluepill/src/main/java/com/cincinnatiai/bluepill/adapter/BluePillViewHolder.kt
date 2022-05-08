package com.cincinnatiai.bluepill.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cincinnatiai.bluepill.data.MenuItem

interface BluePillViewHolderListener {
    fun onSwitchFlipped(position: Int, isChecked: Boolean)
    fun onSpinnerItemChosen(position: Int, childPosition: Int)
}

abstract class BluePillViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindDataToView(data: MenuItem)
}