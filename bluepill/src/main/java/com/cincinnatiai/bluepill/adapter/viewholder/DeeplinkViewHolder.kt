package com.cincinnatiai.bluepill.adapter.viewholder

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.cincinnatiai.bluepill.adapter.BluePillViewHolder
import com.cincinnatiai.bluepill.adapter.BluePillViewHolderListener
import com.cincinnatiai.bluepill.data.DeeplinkItem
import com.cincinnatiai.bluepill.data.MenuItem
import com.cincinnatiai.bluepill.databinding.RowDebugDeeplinkBinding

class DeeplinkViewHolder(
    private val parent: ViewGroup,
    private val binding: RowDebugDeeplinkBinding = RowDebugDeeplinkBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : BluePillViewHolder(binding.root) {

    override fun bindDataToView(data: MenuItem) {
        val item = data as DeeplinkItem
        binding.deeplinkButton.setText(data.title)
        binding.deeplinkButton.setOnClickListener {
            openDeeplink(item)
        }
    }

    private fun openDeeplink(data: DeeplinkItem) {
        try {
            Intent(Intent.ACTION_VIEW).apply {
                setData(Uri.parse(data.url))
                binding.root.context.startActivity(this)
            }
        } catch(e: Throwable) {
            Toast.makeText(binding.root.context, "Link cannot be opeend", Toast.LENGTH_LONG).show()
        }
    }
}