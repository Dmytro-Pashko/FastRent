package com.dpashko.fastrent.presentation.feature.splash

import com.dpashko.fastrent.presentation.navigation.Navigator
import javax.inject.Inject

/**
 * A navigator class that provides methods to navigate from the Splash screen.
 */
class SplashNavigator @Inject constructor(private val navigator: Navigator) {

    /**
     * Navigates to the Dashboard screen.
     */
    fun dashboard() {
        navigator.navigateTo(SplashFragmentDirections.dashboard())
    }

    /**
     * Navigates to the Onboarding screen.
     */
    fun onboarding() {
        navigator.navigateTo(SplashFragmentDirections.onboarding())
    }
}