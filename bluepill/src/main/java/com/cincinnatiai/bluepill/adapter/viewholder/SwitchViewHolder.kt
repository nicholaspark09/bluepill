package com.cincinnatiai.bluepill.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cincinnatiai.bluepill.adapter.BluePillViewHolder
import com.cincinnatiai.bluepill.adapter.BluePillViewHolderListener
import com.cincinnatiai.bluepill.data.MenuItem
import com.cincinnatiai.bluepill.data.SwitchItem
import com.cincinnatiai.bluepill.databinding.RowDebugSwitchBinding

class SwitchViewHolder(
    private val parent: ViewGroup,
    private val listener: BluePillViewHolderListener,
    private val binding: RowDebugSwitchBinding = RowDebugSwitchBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : BluePillViewHolder(binding.root) {

    override fun bindDataToView(data: MenuItem) {
        val switchData = data as SwitchItem
        binding.title.setText(switchData.title)
        binding.rowSwitch.isChecked = switchData.isOn
        binding.rowSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            listener.onSwitchFlipped(adapterPosition, isChecked)
        }
    }
}