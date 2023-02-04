package com.dpashko.fastrent.presentation.feature.splash

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.dpashko.fastrent.R
import com.dpashko.fastrent.presentation.extension.hideNavigationBars
import dagger.android.support.DaggerFragment
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
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
            viewModel.state.collect {
                LOGGER.debug("Observed new state: {}", it)
            }
        }
    }
}
