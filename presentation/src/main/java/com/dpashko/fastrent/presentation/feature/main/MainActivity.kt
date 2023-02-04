package com.dpashko.fastrent.presentation.feature.main

import android.os.Bundle
import com.dpashko.fastrent.R
import dagger.android.support.DaggerAppCompatActivity

/**
 * Main entry point of application.
 */
class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}