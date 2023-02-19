package com.dpashko.fastrent.presentation.navigation

import androidx.navigation.NavController
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A provider class for managing a `NavController` instance.
 *
 * This class is used to provide a `NavController` instance to other classes
 * that need to navigate between different screens or destinations in an
 * Android application.
 */
@Singleton
class NavigationControllerProvider @Inject constructor() {

    /**
     * The `NavController` instance managed by this provider.
     */
    private var controllerRef: WeakReference<NavController>? = null

    /**
     * Sets the `NavController` instance for this provider.
     *
     * @param controller The `NavController` instance to set.
     */
    fun setController(controller: NavController) {
        controllerRef = WeakReference(controller)
    }

    /**
     * Removes the `NavController` instance from this provider.
     */
    fun removeController() {
        controllerRef = null
    }

    /**
     * Returns the current `NavController` instance, or `null` if a
     * `NavController` has not been set.
     *
     */
    fun getController(): NavController? {
        return controllerRef?.get()
    }
}
