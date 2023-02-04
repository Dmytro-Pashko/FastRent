package com.dpashko.fastrent.data.di.module

import android.content.Context
import com.dpashko.fastrent.data.repository.database.AppDatabase
import com.dpashko.fastrent.data.repository.database.DatabaseIdentityRepository
import com.dpashko.fastrent.domain.repository.IdentityRepository
import dagger.Module
import dagger.Provides

@Module
internal class RepositoryModule {

    @Provides
    fun providesIdentityRepository(repository: DatabaseIdentityRepository): IdentityRepository {
        return repository
    }

    @Provides
    fun providesAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}
