package com.kvsoftware.opendota.data.repository

import android.util.Log
import com.kvsoftware.opendota.data.datasource.HeroesLocalDataSource
import com.kvsoftware.opendota.data.datasource.HeroesRemoteDataSource
import com.kvsoftware.opendota.data.entity.HeroEntity
import com.kvsoftware.opendota.data.mapper.toHeroEntity
import com.kvsoftware.opendota.data.mapper.toHeroLocalEntity
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val heroesLocalDataSource: HeroesLocalDataSource,
    private val heroesRemoteDataSource: HeroesRemoteDataSource
) {
    private val tag = "HeroesRepository"

    suspend fun getHeroById(id: Int): HeroEntity? {
        val heroLocalEntity = heroesLocalDataSource.getHeroById(id)
        if (heroLocalEntity == null) {
            Log.d(tag, "Hero not found")
            return null
        }
        return heroLocalEntity.toHeroEntity()
    }

    suspend fun getHeroes(): List<HeroEntity> {
        try {
            val heroRemoteEntities = heroesRemoteDataSource.getHeroes()
            heroesLocalDataSource.updateHeroes(heroRemoteEntities.map { it.toHeroLocalEntity() })
        } catch (e: Exception) {
            Log.d(tag, "Connection failed, using local data source")
        }
        return heroesLocalDataSource.getHeroes().map { it.toHeroEntity() }
    }

}