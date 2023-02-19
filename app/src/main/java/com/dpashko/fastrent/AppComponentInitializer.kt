package com.dpashko.fastrent

import android.content.Context
import androidx.startup.Initializer
import com.dpashko.fastrent.initializer.AppParametersInitializer
import com.dpashko.fastrent.initializer.LoggerInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Class that initiates instances in scope of application lifecycle.
 */
class AppComponentInitializer : Initializer<Unit> {

    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger("AppComponentInitializer")
    }

    /**
     * Initializes the application components.
     *
     * @param context the application context
     */
    override fun create(context: Context) {
        LOGGER.debug("Application component initialized.")
    }

    /**
     * Specifies the list of dependencies needed for the initialization of the application
     * components.
     */
    override fun dependencies() =
        listOf(
            LoggerInitializer::class.java,
            AppParametersInitializer::class.java
        )
}
