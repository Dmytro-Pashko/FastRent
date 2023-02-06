package com.dpashko.fastrent.presentation.feature.splash

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.dpashko.fastrent.databinding.FragmentSplashBinding
import com.dpashko.fastrent.presentation.extension.getTransitionCompletionFlow
import com.dpashko.fastrent.presentation.extension.hideNavigationBars
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

class SplashFragment : DaggerFragment() {

    companion object {
        @JvmStatic
        private val LOGGER: Logger = LoggerFactory.getLogger("SplashFragment")
    }

    @Inject
    lateinit var viewModel: SplashViewModel
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.checkIsOnboardingCompleted()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().hideNavigationBars()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Create a new coroutine in the lifecycleScope of Fragment View.
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state
                .onEach {
                    LOGGER.debug("Observed new state: {}", it)
                }
                // Waits until the main animation transition of the splash screen ends
                // and we receive Completed state of the screen.
                .filter { it is SplashScreenState.Completed }
                .combine(binding.splashMotionLayout.getTransitionCompletionFlow()) { state, _ ->
                    (state as SplashScreenState.Completed).isDashboardCompleted
                }.collect { isDashboardCompleted ->
                    when (isDashboardCompleted) {
                        // TODO(pashkd2): Navigate to Dashboard screen.
                        true -> {}
                        // TODO(pashkd2): Navigate to Onboarding screen.
                        false -> {}
                    }
                }
        }
    }
}
