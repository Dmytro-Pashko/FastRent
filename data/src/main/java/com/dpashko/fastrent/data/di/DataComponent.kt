package com.dpashko.fastrent.data.di

import android.content.Context
import com.dpashko.fastrent.data.di.module.DataModule
import com.dpashko.fastrent.data.di.module.RepositoryModule
import com.dpashko.fastrent.domain.model.AppParameters
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        RepositoryModule::class,
    ]
)
interface DataComponent : RepositoryComponent {

    @Component.Builder
    interface Builder {

        fun appContext(@BindsInstance ctx: Context): Builder

        fun appParameters(@BindsInstance appParameters: AppParameters): Builder

        fun build(): DataComponent
    }
}
