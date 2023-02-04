package com.dpashko.fastrent.domain.di.onboarding

import com.dpashko.fastrent.domain.onboarding.IsOnboardingCompletedUseCase
import dagger.Subcomponent

@Subcomponent
interface OnboardingComponent {

    fun getIsOnboardingCompletedUseCase(): IsOnboardingCompletedUseCase
}