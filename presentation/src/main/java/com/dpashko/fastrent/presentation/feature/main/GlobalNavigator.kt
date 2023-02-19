package com.dpashko.fastrent.presentation.feature.main

import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.dpashko.fastrent.R
import com.dpashko.fastrent.presentation.navigation.NavigationControllerProvider
import com.dpashko.fastrent.presentation.navigation.Navigator
import dagger.hilt.android.scopes.ActivityScoped
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * A navigator for the single app activity. This class provides convenience methods for navigating
 * between the different screens of the app according
 * to global navigation [R.navigation.app_nav_graph]
 * graph.
 *
 * @param controllerProvider The provider for the navigation controller that is used for navigation.
 */
@ActivityScoped
class GlobalNavigator @Inject constructor(
    private val controllerProvider: NavigationControllerProvider
) : Navigator {

    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger("GlobalNavigator")
    }

    /**
     * Navigates to the destination specified by the given [NavDirections] instance.
     *
     * @param destination The [NavDirections] instance that specifies the destination to navigate to
     */
    override fun navigateTo(destination: NavDirections) {
        val navController = controllerProvider.getController()
        if (navController != null) {
            navController.navigate(destination)
        } else {
            LOGGER.error("Navigation controller is not available.")
        }
    }

    /**
     * Navigates to the destination with the specified ID, passing the given arguments and navigation
     * options, if any.
     *
     * @param destinationId The ID of the destination to navigate to.
     * @param args The arguments to pass to the destination, if any.
     * @param navOptions The navigation options to use for the navigation, if any.
     */
    override fun navigateTo(destinationId: Int, args: Bundle?, navOptions: NavOptions?) {
        val navController = controllerProvider.getController()
        if (navController != null) {
            navController.navigate(destinationId, args, navOptions)
        } else {
            LOGGER.error("Navigation controller is not available.")
        }
    }
}