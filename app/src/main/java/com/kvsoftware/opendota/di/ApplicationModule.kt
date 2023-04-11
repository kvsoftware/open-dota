package com.kvsoftware.opendota.di

import com.kvsoftware.opendota.data.RestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for keeping below dependencies arrived as long as application.
 *
 * The dependency which has @Singleton annotation, it will be constructed only one instance across
 * the component.
 * */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Singleton
    fun provideRestClient(): RestClient {
        return RestClient("https://api.opendota.com/api/")
    }
}