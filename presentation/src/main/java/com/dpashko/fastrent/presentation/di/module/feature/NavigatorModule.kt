package com.dpashko.fastrent.presentation.di.module.feature

import com.dpashko.fastrent.presentation.feature.main.GlobalNavigator
import com.dpashko.fastrent.presentation.navigation.NavigationControllerProvider
import com.dpashko.fastrent.presentation.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {

    @Provides
    fun providesGlobalNavigator(controllerProvider: NavigationControllerProvider): GlobalNavigator {
        return GlobalNavigator(controllerProvider)
    }

    @Provides
    fun providesNavigator(navigator: GlobalNavigator): Navigator {
        return navigator
    }
}
