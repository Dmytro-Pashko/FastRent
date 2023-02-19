package com.dpashko.fastrent.presentation.extension

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity

/**
 * Hides the system navigation bars (e.g. the Android navigation bar) for the current activity.
 * The navigation bars will remain hidden until {@link #showNavigationBars()} is called.
 */

fun FragmentActivity.hideNavigationBars() {
    val window = window
    val controller = WindowCompat.getInsetsController(window, window.decorView)
    controller.hide(WindowInsetsCompat.Type.navigationBars())
    controller.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}

/**
 * Shows the system navigation bars (e.g. the Android navigation bar) for the current activity.
 */
fun FragmentActivity.showNavigationBars() {
    val window = window
    val controller = WindowCompat.getInsetsController(window, window.decorView)
    controller.show(WindowInsetsCompat.Type.navigationBars())
}
