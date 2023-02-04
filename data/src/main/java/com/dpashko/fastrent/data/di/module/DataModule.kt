package com.dpashko.fastrent.data.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
internal class DataModule {

    @Provides
    fun providesGson(): Gson = Gson()
}