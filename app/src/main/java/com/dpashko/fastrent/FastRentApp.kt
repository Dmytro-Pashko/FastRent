package com.dpashko.fastrent

import android.app.Application
import androidx.startup.AppInitializer
import dagger.hilt.android.HiltAndroidApp

/**
 * The [FastRentApp] class is the entry point for the FastRent application.
 * It extends the Application class and is annotated with @HiltAndroidApp, which enables
 * Hilt dependency injection in the app.
 */
@HiltAndroidApp
class FastRentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInitializer.getInstance(this)
            .initializeComponent(AppComponentInitializer::class.java)
    }
}
