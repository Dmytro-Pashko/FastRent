package com.dpashko.fastrent.data.di.module

import com.dpashko.fastrent.data.repository.database.DatabaseIdentityRepository
import com.dpashko.fastrent.domain.repository.IdentityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideIdentityRepository(
        databaseIdentityRepository: DatabaseIdentityRepository
    ): IdentityRepository {
        return databaseIdentityRepository;
    }
}
