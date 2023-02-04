package com.dpashko.fastrent.presentation.extension

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.hideNavigationBars() {
    val window = window
    val controller = WindowCompat.getInsetsController(window, window.decorView)
    controller.hide(WindowInsetsCompat.Type.navigationBars())
    controller.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}