package com.eddiej.searchbeers.global

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : android.app.Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
    }
}