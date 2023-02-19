package com.dpashko.fastrent.presentation.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

/**
 * Base Navigator that uses the Navigation Component library
 * to navigate between screens.
 *
 * @param navHostController Navigation controller from main app [NavHost]
 */
interface Navigator {

    /**
     * Navigates to the specified destination using the NavDirections object.
     *
     * @param destination the NavDirections object that represents the destination screen
     */
    fun navigateTo(destination: NavDirections)

    /**
     * Navigates to the specified destination using the resource ID of the action, the arguments
     * bundle, and the navigation options.
     *
     * @param destinationId the resource ID of the action
     * @param args the arguments bundle (can be null)
     * @param navOptions the navigation options (can be null)
     */
    fun navigateTo(
        @IdRes destinationId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null
    )
}
