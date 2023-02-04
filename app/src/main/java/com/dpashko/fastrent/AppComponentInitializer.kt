package com.dpashko.fastrent

import android.content.Context
import androidx.startup.AppInitializer
import androidx.startup.Initializer
import com.dpashko.fastrent.data.di.DaggerDataComponent
import com.dpashko.fastrent.di.AppComponent
import com.dpashko.fastrent.di.DaggerAppComponent
import com.dpashko.fastrent.domain.di.DaggerDomainComponent
import com.dpashko.fastrent.initializer.AppParametersInitializer
import com.dpashko.fastrent.initializer.LoggerInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Class that starts app components initializer.
 */
class AppComponentInitializer : Initializer<AppComponent> {

    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger("AppComponentInitializer")
    }

    override fun create(context: Context): AppComponent {
        val appParameters =
            AppInitializer.getInstance(context)
                .initializeComponent(AppParametersInitializer::class.java)
        val dataComponent = DaggerDataComponent.builder()
            .appContext(context)
            .appParameters(appParameters)
            .build()
        LOGGER.debug("Data component initialized.")
        val domainComponent = DaggerDomainComponent.builder()
            .identityRepository(dataComponent.getIdentityRepository())
            .appParameters(appParameters)
            .build()
        LOGGER.debug("Domain component initialized.")
        val appComponent = DaggerAppComponent.builder()
            .appContext(context)
            .domainComponent(domainComponent)
            .build()
        LOGGER.debug("Presentation component initialized.")
        return appComponent
    }

    override fun dependencies() =
        listOf(
            LoggerInitializer::class.java,
            AppParametersInitializer::class.java
        )
}
