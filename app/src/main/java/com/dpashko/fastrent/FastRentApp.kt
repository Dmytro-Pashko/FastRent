package com.dpashko.fastrent

import android.app.Application
import androidx.startup.AppInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FastRentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInitializer.getInstance(this)
            .initializeComponent(AppComponentInitializer::class.java)
    }
}
