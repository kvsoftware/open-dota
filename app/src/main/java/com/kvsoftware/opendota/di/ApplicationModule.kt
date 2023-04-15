package com.kvsoftware.opendota.di

import android.content.Context
import androidx.room.Room
import com.kvsoftware.opendota.data.local.AppDatabase
import com.kvsoftware.opendota.data.local.FavoriteDao
import com.kvsoftware.opendota.data.local.HeroDao
import com.kvsoftware.opendota.data.remote.HeroApi
import com.kvsoftware.opendota.data.remote.RestClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideHeroApi(restClient: RestClient): HeroApi {
        return restClient.createService(HeroApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "open_dota").build()
    }

    @Provides
    @Singleton
    fun provideHeroDao(appDatabase: AppDatabase): HeroDao {
        return appDatabase.heroDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: AppDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }
}