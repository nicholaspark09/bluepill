package com.cincinnatiai.bluepill.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK

object Phoenix {

    fun restart(context: Context, intent: Intent = getRestartIntent(context)) {
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        (context as? Activity)?.let { it.finish() }
        Runtime.getRuntime().exit(0)
    }

    private fun getRestartIntent(context: Context) =
        context.packageManager.getLaunchIntentForPackage(context.packageName)
            ?: throw IllegalStateException("No launch intent for package")
}