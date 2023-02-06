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
    private val stateController: MutableStateFlow<SplashScreenState> =
        MutableStateFlow(SplashScreenState.Idle)

    // The UI collects from this StateFlow to get its state updates.
    val state: StateFlow<SplashScreenState> = stateController.asStateFlow()

    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger("SplashViewModel")
    }

    fun checkIsOnboardingCompleted() {
        stateController.value = SplashScreenState.Loading
        viewModelScope.launch {
            val isOnboardingCompleted = isOnboardingCompleted.invoke()
            LOGGER.debug("Onboarding flow is completed: {}", isOnboardingCompleted)
            stateController.value = SplashScreenState.Completed(isOnboardingCompleted)
        }
    }
}

sealed class SplashScreenState {

    object Idle : SplashScreenState()

    object Loading : SplashScreenState()

    class Completed(val isDashboardCompleted: Boolean) : SplashScreenState()
}
