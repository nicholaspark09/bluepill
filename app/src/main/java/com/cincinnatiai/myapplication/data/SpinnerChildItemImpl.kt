package com.cincinnatiai.myapplication.data

import com.cincinnatiai.bluepill.data.SpinnerChildItem

data class SpinnerChildItemImpl(
    override val title: String,
    val url: String
) : SpinnerChildItem {
    override fun <T> getValue() = url as T
}