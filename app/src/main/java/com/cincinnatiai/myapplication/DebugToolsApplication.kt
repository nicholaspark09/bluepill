package com.cincinnatiai.myapplication

import android.app.Application
import com.cincinnatiai.bluepill.BluePillLibrary
import com.cincinnatiai.myapplication.provider.MenuProvider

class DebugToolsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupBluePill()
    }

    /**
     * This shows you how to setup BluePill
     */
    private fun setupBluePill() {
        if (BuildConfig.DEBUG) {
            BluePillLibrary.initialize(MenuProvider(applicationContext))
        }
    }
}