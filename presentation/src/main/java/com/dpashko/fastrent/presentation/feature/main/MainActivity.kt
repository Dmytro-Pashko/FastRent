package com.dpashko.fastrent.presentation.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.dpashko.fastrent.R
import com.dpashko.fastrent.databinding.ActivityMainBinding
import com.dpashko.fastrent.presentation.navigation.NavigationControllerProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main entry point of application.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationControllerProvider: NavigationControllerProvider
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navigationControllerProvider.setController(navHostFragment.navController)
    }

    override fun onDestroy() {
        navigationControllerProvider.removeController()
        super.onDestroy()
    }
}
