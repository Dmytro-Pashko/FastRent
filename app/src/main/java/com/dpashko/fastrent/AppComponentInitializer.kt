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

    override fun create(context: Context) {
        LOGGER.debug("Application component initialized.")
    }

    override fun dependencies() =
        listOf(
            LoggerInitializer::class.java,
            AppParametersInitializer::class.java
        )
}
