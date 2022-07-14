package com.tech.umr.Util

import android.app.Application

val prefs: MyPreferences by lazy {
    App.prefs!!
}

class App : Application() {

    companion object {
        var prefs: MyPreferences? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = MyPreferences(applicationContext)
    }
}