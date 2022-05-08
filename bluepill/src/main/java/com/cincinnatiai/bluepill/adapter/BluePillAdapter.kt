package com.cincinnatiai.bluepill.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cincinnatiai.bluepill.adapter.viewholder.DeeplinkViewHolder
import com.cincinnatiai.bluepill.adapter.viewholder.SpinnerViewHolder
import com.cincinnatiai.bluepill.adapter.viewholder.SwitchViewHolder
import com.cincinnatiai.bluepill.data.*

interface BluePillAdapterListener {
    fun onSwitchUpdated(switchItem: SwitchItem)
    fun onSpinnerUpdate(spinnerItem: SpinnerItem)
}

class BluePillAdapter(
    private val adapterListener: BluePillAdapterListener
) : RecyclerView.Adapter<BluePillViewHolder>(), BluePillViewHolderListener {

    private val items = mutableListOf<MenuItem>()

    fun setItems(items: List<MenuItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluePillViewHolder {
        return when (viewType) {
            ITEM_TYPE_SWITCH -> SwitchViewHolder(parent, this)
            ITEM_TYPE_SPINNER -> SpinnerViewHolder(parent, this)
            ITEM_TYPE_DEEPLINK -> DeeplinkViewHolder(parent)
            else -> throw IllegalArgumentException("No view type matching")
        }
    }

    override fun onBindViewHolder(holder: BluePillViewHolder, position: Int) {
        holder.bindDataToView(items[position])
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].itemViewType

    override fun onSwitchFlipped(position: Int, isChecked: Boolean) {
        (items[position] as? SwitchItem)?.copy(isOn = isChecked)?.let {
            adapterListener.onSwitchUpdated(it)
            updateInternalItem(position, it)
        }
    }

    override fun onSpinnerItemChosen(position: Int, childPosition: Int) {
        (items[position] as? SpinnerItem)?.copy(selectedPosition = childPosition)?.let {
            adapterListener.onSpinnerUpdate(it)
            updateInternalItem(position, it)
        }
    }

    private fun updateInternalItem(position: Int, menuItem: MenuItem) {
        synchronized(this) {
            items[position] = menuItem
        }
    }
}