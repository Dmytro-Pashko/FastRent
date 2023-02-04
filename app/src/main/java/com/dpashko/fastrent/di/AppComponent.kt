package com.dpashko.fastrent.di

import android.content.Context
import com.dpashko.fastrent.FastRentApp
import com.dpashko.fastrent.domain.di.DomainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    dependencies = [
        DomainComponent::class,

    ],
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
    ]
)
interface AppComponent : AndroidInjector<FastRentApp> {

    @Component.Builder
    interface Builder {

        fun appContext(@BindsInstance ctx: Context): Builder

        fun domainComponent(domain: DomainComponent): Builder

        fun build(): AppComponent
    }
}