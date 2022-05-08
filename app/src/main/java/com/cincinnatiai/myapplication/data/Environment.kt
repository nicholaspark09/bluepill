package com.cincinnatiai.myapplication.data

enum class Environment(val url: String) {
    DEBUG("https://debug.cincyai.com/"),
    QA("https://qa.cincyai.com/"),
    STAGING("https://staging.cincyai.com/"),
    PROD("https://prod.cincyai.com/")
}