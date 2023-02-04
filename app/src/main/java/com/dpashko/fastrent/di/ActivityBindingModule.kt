package com.dpashko.fastrent.di

import com.dpashko.fastrent.presentation.feature.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}

