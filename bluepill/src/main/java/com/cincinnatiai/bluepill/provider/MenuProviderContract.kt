package com.cincinnatiai.bluepill.provider

import com.cincinnatiai.bluepill.data.MenuItem

interface MenuProviderContract {

    suspend fun provideMenuItems(): List<MenuItem>

    suspend fun updateMenuItem(menuItem: MenuItem)
}