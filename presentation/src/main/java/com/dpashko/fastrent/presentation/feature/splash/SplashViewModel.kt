package com.dpashko.fastrent.presentation.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpashko.fastrent.domain.onboarding.IsOnboardingCompletedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * ViewModel that handles state of Splash Screen.
 */
class SplashViewModel @Inject constructor(
    private val isOnboardingCompleted: IsOnboardingCompletedUseCase
) : ViewModel() {

    // Backing property to avoid state updates from other classes and initiates default state.
    private val stateController: MutableStateFlow<SlashScreenState> =
        MutableStateFlow(SlashScreenState.Idle())

    // The UI collects from this StateFlow to get its state updates.
    val state: StateFlow<SlashScreenState> = stateController.asStateFlow()

    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger("SplashViewModel")
    }

    fun checkIsOnboardingCompleted() {
        stateController.value = SlashScreenState.Loading()
        viewModelScope.launch {
            val isOnboardingCompleted = isOnboardingCompleted.invoke()
            LOGGER.debug("Onboarding flow is completed: {}", isOnboardingCompleted)
            stateController.value = SlashScreenState.Completed(isOnboardingCompleted)
        }
    }
}

sealed class SlashScreenState {

    open class Idle : SlashScreenState()

    open class Loading : SlashScreenState()

    open class Completed(isDashboardCompleted: Boolean) : SlashScreenState()
}
