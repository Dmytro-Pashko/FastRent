package com.dpashko.fastrent.data.di

import com.dpashko.fastrent.domain.repository.IdentityRepository

/**
 * DI interface that specifies list of object related to repositories that will be xposed from
 * [DataComponent].
 */
interface RepositoryComponent {

    fun getIdentityRepository(): IdentityRepository
}
