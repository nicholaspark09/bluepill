package com.cincinnatiai.bluepill

import android.content.Context
import com.cincinnatiai.bluepill.provider.MenuProviderContract
import com.cincinnatiai.bluepill.repository.MenuRepository
import com.cincinnatiai.bluepill.repository.MenuRepositoryContract
import com.cincinnatiai.bluepill.ui.DebugActivity


class BluePillLibrary private constructor(
    private val menuProvider: MenuProviderContract
) {

    val menuRepository: MenuRepositoryContract by lazy {
        MenuRepository(menuProvider)
    }

    fun openDebugActivity(context: Context) {
        context.startActivity(DebugActivity.newIntent(context))
    }

    companion object {
        @Volatile
        var INSTANCE: BluePillLibrary? = null

        @JvmStatic
        fun initialize(menuProvider: MenuProviderContract) {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = BluePillLibrary(menuProvider)
                    }
                }
            }
        }

        @JvmStatic
        fun open(context: Context) {
            getInstance().openDebugActivity(context)
        }

        @JvmStatic
        fun getInstance(): BluePillLibrary = INSTANCE ?: throw IllegalArgumentException("")
    }
}