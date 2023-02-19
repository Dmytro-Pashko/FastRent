package com.dpashko.fastrent.data.di.module

import android.content.Context
import com.dpashko.fastrent.data.repository.database.AppDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Module that provides dependencies related to data module.
 */
@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    /**
     * Provides a [Gson] instance for parsing JSON data.
     */
    @Provides
    fun providesGson(): Gson = Gson()

    /**
     * Provides the [AppDatabase] instance for accessing the app's database.
     * @param context The [Context] used to create the [AppDatabase] instance.
     * @return The [AppDatabase] instance.
     */
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}
