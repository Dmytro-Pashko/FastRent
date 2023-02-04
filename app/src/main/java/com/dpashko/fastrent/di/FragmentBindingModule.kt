package com.dpashko.fastrent.di

import com.dpashko.fastrent.presentation.feature.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun splashFragment(): SplashFragment
}

