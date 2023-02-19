package com.dpashko.fastrent.presentation.feature.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import com.dpashko.fastrent.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment class representing the onboarding screen, which provides users with an introduction
 * to the app and its features.
 */
@AndroidEntryPoint
class OnboardingFragment : AppCompatDialogFragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }
}
