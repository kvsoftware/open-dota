package com.kvsoftware.opendota.data.repository

import android.util.Log
import com.kvsoftware.opendota.data.entity.HeroEntity
import com.kvsoftware.opendota.data.datasource.HeroesLocalDataSource
import com.kvsoftware.opendota.data.datasource.HeroesRemoteDataSource
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val heroesLocalDataSource: HeroesLocalDataSource,
    private val heroesRemoteDataSource: HeroesRemoteDataSource
) {
    private val tag = "HeroesRepository"

    suspend fun getHeroes(apiKey: String): List<HeroEntity> {
        try {
            val heroes = heroesRemoteDataSource.getHeroes(apiKey)
            heroesLocalDataSource.updateHeroes(heroes)
        } catch (e: Exception) {
            Log.d(tag, "Connection failed, using local data source")
        }
        return heroesLocalDataSource.getHeroes()
    }
}