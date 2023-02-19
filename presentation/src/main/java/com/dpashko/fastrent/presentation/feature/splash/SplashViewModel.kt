package com.dpashko.fastrent.presentation.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpashko.fastrent.domain.onboarding.IsOnboardingCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * A ViewModel class for the Splash screen. This class is responsible for managing the state and
 * behavior of the splash Screen.
 *
 * @property isOnboardingCompleted A use case that checks whether the onboarding flow
 * has been completed.
 * @property navigator An interface that defines API for navigation in Splash screen.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isOnboardingCompleted: IsOnboardingCompletedUseCase,
    private val navigator: SplashNavigator
) : ViewModel() {

    /**
     * It is a [MutableStateFlow] that is used to store the current state of the splash screen.
     */
    private val stateController: MutableStateFlow<SplashScreenState> =
        MutableStateFlow(SplashScreenState.Idle)

    /**
     * It is a [MutableStateFlow] that is used to store the current state of the
     * Splash screen animation.
     */
    private val animationController: MutableStateFlow<SplashAnimationState> =
        MutableStateFlow(SplashAnimationState.NOT_STARTED)

    /**
     * The [state] property is a [StateFlow] that exposes the current state
     * of the splash screen to the UI layer.
     */
    val state: StateFlow<SplashScreenState> = stateController.asStateFlow()

    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger("SplashViewModel")
    }

    /**
     *  Called when the splash screen is first displayed.
     *  It sets the state to Loading, launches a coroutine to check whether the
     *  onboarding flow has been completed, and sets the state to Completed when the coroutine
     *  completes. Waits till [animationController] notifies about completed splash screen
     *  animation.
     */
    fun loadInitialDataAndWaitAnimationCompletion() {
        LOGGER.debug("Started splash initialization.")
        viewModelScope.launch {
            checkIsOnboardingCompleted()
            stateController.filter { it is SplashScreenState.Initialized }
                .combine(
                    animationController.filter { it == SplashAnimationState.COMPLETED }
                ) { state, _ ->
                    (state as SplashScreenState.Initialized).isOnboardingCompleted
                }.collect { isOnboardingCompleted ->
                    LOGGER.debug("Splash screen initialization completed.")
                    when (isOnboardingCompleted) {
                        true -> navigator.dashboard()
                        false -> navigator.onboarding()
                    }
                }
        }
    }

    private fun checkIsOnboardingCompleted() {
        stateController.value = SplashScreenState.Loading
        viewModelScope.launch {
            val isOnboardingCompleted = isOnboardingCompleted.invoke()
            LOGGER.debug("Onboarding flow is completed: {}", isOnboardingCompleted)
            stateController.value = SplashScreenState.Initialized(isOnboardingCompleted)
        }
    }

    /**
     * Called when the splash screen animation is completed.
     */
    fun onSplashAnimationCompleted() {
        animationController.value = SplashAnimationState.COMPLETED
        LOGGER.debug("Splash screen animation completed.")
    }

    /**
     * Called when the splash screen animation is started.
     */
    fun onSplashAnimationStarted() {
        animationController.value = SplashAnimationState.IN_PROGRESS
        LOGGER.debug("Splash screen animation started.")
    }
}

/**
 * Sealed class representing the possible states of the Splash screen.
 */
sealed class SplashScreenState {

    /**
     * The idle state of the splash screen.
     */
    object Idle : SplashScreenState()

    /**
     * The loading state of the splash screen.
     */
    object Loading : SplashScreenState()

    /**
     * The completed state of the splash screen.
     * It contains a [Boolean] value that indicates whether the onboarding flow has been completed.
     *
     * @property isOnboardingCompleted A [Boolean]
     * value that indicates whether the onboarding flow has been completed.
     */
    class Initialized(val isOnboardingCompleted: Boolean) : SplashScreenState()
}

enum class SplashAnimationState {

    /**
     * The state when the splash screen animation has not yet started.
     */
    NOT_STARTED,

    /**
     * The state when the splash screen animation started and has not yet completed.
     */
    IN_PROGRESS,

    /**
     * The state when the splash screen animation completed.
     */
    COMPLETED,
}
