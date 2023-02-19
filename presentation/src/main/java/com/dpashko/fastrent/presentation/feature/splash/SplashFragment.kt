package com.dpashko.fastrent.presentation.feature.splash

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import com.dpashko.fastrent.databinding.FragmentSplashBinding
import com.dpashko.fastrent.presentation.extension.hideNavigationBars
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : AppCompatDialogFragment() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadInitialDataAndWaitAnimationCompletion()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().hideNavigationBars()
//        binding.splashMotionLayout.addTransitionListener(this)
    }

    fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
        viewModel.onSplashAnimationStarted()
    }

    fun onTransitionChange(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
        progress: Float
    ) {
    }

    fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
        viewModel.onSplashAnimationCompleted()
    }

    fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {
    }
}
