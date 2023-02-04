package com.dpashko.fastrent.initializer

import android.content.Context
import androidx.startup.Initializer
import com.dpashko.fastrent.domain.model.AppParameters
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

/**
 * Initialises app parameters.
 */
class AppParametersInitializer : Initializer<AppParameters> {

    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger("AppParametersInitializer")
        private const val APP_PARAMS_FILE: String = "app_params.json"
    }

    override fun create(context: Context): AppParameters {
        return parseAppParameters(context)
    }

    private fun parseAppParameters(context: Context): AppParameters {
        val configFile = File(context.getExternalFilesDir(null), APP_PARAMS_FILE)
        if (configFile.exists()) {
            try {
                return Gson().fromJson(configFile.readText(), AppParameters::class.java)
            } catch (exception: JsonSyntaxException) {
                LOGGER.error("Failed reading app parameters file: ", exception)
            }
        } else {
            LOGGER.info("App parameters file '{}' does not exist.", configFile.absolutePath)
        }
        return AppParameters()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
        LoggerInitializer::class.java
    )
}

