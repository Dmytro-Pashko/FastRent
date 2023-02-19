package com.dpashko.fastrent.presentation.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import com.dpashko.fastrent.databinding.FragmentDashboradBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment that displays the main dashboard screen of the app.
 */
@AndroidEntryPoint
class DashboardFragment : AppCompatDialogFragment() {

    // In this case, ViewModel property is private.
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var binding: FragmentDashboradBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboradBinding.inflate(layoutInflater)
        return binding.root
    }
}
