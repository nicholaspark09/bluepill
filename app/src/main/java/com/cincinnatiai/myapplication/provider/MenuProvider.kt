package com.cincinnatiai.myapplication.provider

import android.content.Context
import android.content.SharedPreferences
import com.cincinnatiai.bluepill.data.MenuItem
import com.cincinnatiai.bluepill.data.SpinnerItem
import com.cincinnatiai.bluepill.data.SwitchItem
import com.cincinnatiai.bluepill.provider.MenuProviderContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * You can create your own menuprovider implementation that uses any type of persistence you
 * want; this is just an example
 */
class MenuProvider(
    context: Context,
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(KEY_SHARED_MENU, Context.MODE_PRIVATE),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuProviderContract {

    companion object {
        private const val KEY_SHARED_MENU = "key:debugtools"
    }

    private val sectionOneProvider: SectionOneProvider by lazy { SectionOneProvider(sharedPrefs) }

    override suspend fun provideMenuItems(): List<MenuItem> = withContext(dispatcher) {
        mutableListOf<MenuItem>().apply {
            addAll(sectionOneProvider.providerSectionOne())
        }
    }

    override suspend fun updateMenuItem(menuItem: MenuItem) {
        val editor = sharedPrefs.edit()
        when(menuItem) {
            is SwitchItem -> editor.putBoolean(menuItem.key, menuItem.isOn).apply()
            is SpinnerItem -> editor.putInt(menuItem.key, menuItem.selectedPosition).apply()
        }
    }
}