package com.cincinnatiai.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cincinnatiai.bluepill.BluePillLibrary
import com.cincinnatiai.bluepill.ui.DebugActivity
import com.cincinnatiai.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // This shows you how to open the Debug Activity
        findViewById<Button>(R.id.debug).setOnClickListener {
            BluePillLibrary.open(this)
        }
    }
}