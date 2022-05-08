package com.cincinnatiai.bluepill.repository

import com.cincinnatiai.bluepill.data.MenuItem
import com.cincinnatiai.bluepill.provider.MenuProviderContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MenuRepositoryContract {
    suspend fun fetchMenuItems(): List<MenuItem>
    suspend fun saveMenuItem(item: MenuItem)
    suspend fun saveAllItems(items: List<MenuItem>)
}

class MenuRepository(
    private val menuProvider: MenuProviderContract,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MenuRepositoryContract {



    /**
     * The list provided by the application and the
     * persisted state have to be reconciled here
     */
    override suspend fun fetchMenuItems() = withContext(dispatcher) {
        return@withContext menuProvider.provideMenuItems()
    }

    override suspend fun saveMenuItem(item: MenuItem) {
        menuProvider.updateMenuItem(item)
    }

    override suspend fun saveAllItems(items: List<MenuItem>) {
        items.forEach { menuProvider.updateMenuItem(it) }
    }
}