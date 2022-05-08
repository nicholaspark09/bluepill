package com.cincinnatiai.myapplication.provider

import android.content.SharedPreferences
import com.cincinnatiai.bluepill.data.MenuItem
import com.cincinnatiai.bluepill.data.SpinnerItem
import com.cincinnatiai.bluepill.data.SwitchItem
import com.cincinnatiai.myapplication.R
import com.cincinnatiai.myapplication.data.Environment
import com.cincinnatiai.myapplication.data.SpinnerChildItemImpl

class SectionOneProvider(
    private val sharedPref: SharedPreferences
) {

    suspend fun providerSectionOne(): List<MenuItem> {
        val items = mutableListOf<MenuItem>()
        items.add(fetchEnvironment())
        items.add(fetchAnalyticsSetting())
        return items
    }

    private suspend fun fetchEnvironment(): MenuItem {
        val chosenEnvironment = sharedPref.getInt(KEY_ENVIRONMENT, Environment.DEBUG.ordinal)
        return SpinnerItem(
            key = KEY_ENVIRONMENT,
            title = R.string.environment,
            selectedPosition = chosenEnvironment,
            childItems =
            Environment.values().map {
                SpinnerChildItemImpl(it.name.capitalize(), it.url)
            }
        )
    }

    private suspend fun fetchAnalyticsSetting(): MenuItem = SwitchItem(
        key = KEY_ANALYTICS,
        isOn = sharedPref.getBoolean(KEY_ANALYTICS, false),
        title = R.string.debug_analytics
    )

    companion object {
        const val KEY_ENVIRONMENT = "key:environment"
        const val KEY_ANALYTICS = "key:analytics"
    }
}