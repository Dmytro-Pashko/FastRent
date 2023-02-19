package com.dpashko.fastrent.domain.model

/**
 * Data class containing various application parameters that affect its behaviour. While default
 * parameters achieve default app behaviour per app requirements, they may also be overridden for
 * test purposes.
 * Instance of this class is mapped directly from the json configuration file via Gson. In order to
 * make sure that Kotlin's default values are used during json parsing, default value is specified
 * for each individual field as described in
 * https://proandroiddev.com/safe-parsing-kotlin-data-classes-with-gson-4d560fe3cdd2.
 */
data class AppParameters(

    /**
     * Default timeout for all network request sent from application.
     */
    val defaultNetworkRequestTimeoutSec: Int = 10,
)
