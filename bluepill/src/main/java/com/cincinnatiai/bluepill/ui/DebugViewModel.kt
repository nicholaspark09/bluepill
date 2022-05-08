package com.cincinnatiai.bluepill.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cincinnatiai.bluepill.BluePillLibrary
import com.cincinnatiai.bluepill.data.MenuItem
import com.cincinnatiai.bluepill.repository.MenuRepositoryContract
import kotlinx.coroutines.launch

class DebugViewModel : ViewModel() {

    private val repo: MenuRepositoryContract by lazy {
        BluePillLibrary.getInstance().menuRepository
    }
    private val _items = MutableLiveData<List<MenuItem>>()
    val items: LiveData<List<MenuItem>> = _items

    fun fetchItems() {
        viewModelScope.launch {
            val items = repo.fetchMenuItems()
            _items.postValue(items)
        }
    }

    fun persistItem(menuItem: MenuItem) {
        viewModelScope.launch {
            repo.saveMenuItem(menuItem)
        }
    }

}