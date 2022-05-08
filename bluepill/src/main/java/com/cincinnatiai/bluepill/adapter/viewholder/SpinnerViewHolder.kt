package com.cincinnatiai.bluepill.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.cincinnatiai.bluepill.adapter.BluePillViewHolder
import com.cincinnatiai.bluepill.adapter.BluePillViewHolderListener
import com.cincinnatiai.bluepill.data.MenuItem
import com.cincinnatiai.bluepill.data.SpinnerItem
import com.cincinnatiai.bluepill.data.SwitchItem
import com.cincinnatiai.bluepill.databinding.RowDebugSpinnerBinding

class SpinnerViewHolder(
    private val parent: ViewGroup,
    private val listener: BluePillViewHolderListener,
    private val binding: RowDebugSpinnerBinding = RowDebugSpinnerBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : BluePillViewHolder(binding.root) {

    lateinit var spinnerAdapter: ArrayAdapter<String>

    override fun bindDataToView(data: MenuItem) {
        val data = data as SpinnerItem
        spinnerAdapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_dropdown_item,
            data.childItems.map {
                it.title
            }
        )
        binding.title.setText(data.title)
        binding.spinner.apply {
            adapter = spinnerAdapter
            setSelection(data.selectedPosition)
            onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    listener.onSpinnerItemChosen(this@SpinnerViewHolder.adapterPosition, position)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) { } // TODO

            }
        }
    }
}