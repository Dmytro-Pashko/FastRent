package com.dpashko.fastrent.domain.di

import com.dpashko.fastrent.domain.di.onboarding.OnboardingComponent
import com.dpashko.fastrent.domain.model.AppParameters
import com.dpashko.fastrent.domain.repository.IdentityRepository
import dagger.BindsInstance
import dagger.Component

@Component
interface DomainComponent : OnboardingComponent {

    @Component.Builder
    interface Builder {

        /**
         * Add Identity Repository Implementation as component input.
         */
        fun identityRepository(@BindsInstance identityRepository: IdentityRepository): Builder

        fun appParameters(@BindsInstance appParameters: AppParameters): Builder

        fun build(): DomainComponent
    }
}