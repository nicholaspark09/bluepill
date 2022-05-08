package com.cincinnatiai.bluepill.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cincinnatiai.bluepill.BluePillLibrary
import com.cincinnatiai.bluepill.adapter.BluePillAdapter
import com.cincinnatiai.bluepill.adapter.BluePillAdapterListener
import com.cincinnatiai.bluepill.data.SpinnerItem
import com.cincinnatiai.bluepill.data.SwitchItem
import com.cincinnatiai.bluepill.repository.MenuRepositoryContract
import com.cincinnatiai.bluepill.utils.Phoenix
import com.cincinnatiai.bluepill.databinding.ActivityDebugBinding
import kotlinx.coroutines.launch

class DebugActivity : AppCompatActivity(), BluePillAdapterListener {

    private lateinit var binding: ActivityDebugBinding
    private var bluePillAdapter: BluePillAdapter? = null
    private val viewModel: DebugViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDebugBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        setRecyclerView()
        binding.restartButton.setOnClickListener {
            Phoenix.restart(this)
        }
        viewModel.fetchItems()
    }

    private fun setRecyclerView() {
        bluePillAdapter = BluePillAdapter(this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@DebugActivity)
            adapter = bluePillAdapter
        }
        viewModel.items.observe(this) {
            when (it) {
                null -> { } // NOOP
                else -> bluePillAdapter?.setItems(it)
            }
        }
    }

    override fun onSwitchUpdated(switchItem: SwitchItem) {
        viewModel.persistItem(switchItem)
    }

    override fun onSpinnerUpdate(spinnerItem: SpinnerItem) {
        viewModel.persistItem(spinnerItem)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, DebugActivity::class.java)
    }
}