package com.cincinnatiai.bluepill.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cincinnatiai.bluepill.data.MenuItem
import com.cincinnatiai.bluepill.data.SwitchItem
import com.cincinnatiai.bluepill.databinding.RowDebugSwitchBinding

interface BluePillViewHolderListener {
    fun onSwitchFlipped(position: Int, isChecked: Boolean)
    fun onSpinnerItemChosen(position: Int, childPosition: Int)
}

abstract class BluePillViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindDataToView(data: MenuItem)
}