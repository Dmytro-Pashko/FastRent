package com.dpashko.fastrent

import androidx.startup.AppInitializer
import dagger.android.support.DaggerApplication

class FastRentApp : DaggerApplication() {

    override fun applicationInjector() = AppInitializer.getInstance(this)
        .initializeComponent(AppComponentInitializer::class.java)
}
