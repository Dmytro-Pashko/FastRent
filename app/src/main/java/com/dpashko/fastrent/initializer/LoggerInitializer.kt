package com.dpashko.fastrent.initializer

import android.content.Context
import androidx.startup.Initializer
import com.dpashko.fastrent.logger.LogbackConfigurator

/**
 * Initialises and configure App Logging.
 */
class LoggerInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        LogbackConfigurator().apply {
            configureAppLogging(context)
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}