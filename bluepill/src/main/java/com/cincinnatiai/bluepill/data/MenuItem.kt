package com.cincinnatiai.bluepill.data

import androidx.annotation.StringRes
import java.io.Serializable

interface MenuItem {
    val key: String
    val title: Int
    val itemViewType: Int

    fun <T> getValue(): T
}

interface SpinnerChildItem {
    val title: String

    fun <T> getValue(): T
}

data class SwitchItem(
    override val key: String,
    @StringRes
    override val title: Int,
    val isOn: Boolean = false,
    override val itemViewType: Int = ITEM_TYPE_SWITCH
) : MenuItem, Serializable {

    override fun <T> getValue() = isOn as T
}

data class SpinnerItem(
    override val key: String,
    @StringRes
    override val title: Int,
    val selectedPosition: Int = 0,
    val childItems: List<SpinnerChildItem>,
    override val itemViewType: Int = ITEM_TYPE_SPINNER
) : MenuItem, Serializable {

    override fun <T> getValue() = selectedPosition as T

}

data class DeeplinkItem(
    override val key: String,
    @StringRes
    override val title: Int,
    val url: String,
    override val itemViewType: Int = ITEM_TYPE_DEEPLINK
) : MenuItem, Serializable {

    override fun <T> getValue() = url as T
}

const val ITEM_TYPE_SWITCH = 0
const val ITEM_TYPE_SPINNER = 1
const val ITEM_TYPE_DEEPLINK = 2