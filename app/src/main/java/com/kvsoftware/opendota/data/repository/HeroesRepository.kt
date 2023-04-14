package com.kvsoftware.opendota.data.repository

import android.util.Log
import com.kvsoftware.opendota.data.entity.HeroRemoteEntity
import com.kvsoftware.opendota.data.datasource.HeroesLocalDataSource
import com.kvsoftware.opendota.data.datasource.HeroesRemoteDataSource
import com.kvsoftware.opendota.data.entity.HeroEntity
import com.kvsoftware.opendota.data.entity.HeroLocalEntity
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
        return mapLocalEntityToEntity(heroLocalEntity)
    }

    suspend fun getHeroes(apiKey: String): List<HeroEntity> {
        try {
            val heroRemoteEntities = heroesRemoteDataSource.getHeroes(apiKey)
            heroesLocalDataSource.updateHeroes(heroRemoteEntities.map { mapRemoteToLocalEntity(it) })
        } catch (e: Exception) {
            Log.d(tag, "Connection failed, using local data source")
        }
        return heroesLocalDataSource.getHeroes().map { mapLocalEntityToEntity(it) }
    }

    private fun mapRemoteToLocalEntity(heroRemoteEntity: HeroRemoteEntity): HeroLocalEntity {
        return HeroLocalEntity(
            id = heroRemoteEntity.id,
            name = heroRemoteEntity.name,
            localizedName = heroRemoteEntity.localizedName,
            primaryAttr = heroRemoteEntity.primaryAttr,
            attackType = heroRemoteEntity.attackType,
            roles = heroRemoteEntity.roles,
            legs = heroRemoteEntity.legs
        )
    }

    private fun mapLocalEntityToEntity(heroLocalEntity: HeroLocalEntity): HeroEntity {
        return HeroEntity(
            id = heroLocalEntity.id,
            name = heroLocalEntity.name,
            localizedName = heroLocalEntity.localizedName,
            primaryAttr = heroLocalEntity.primaryAttr,
            attackType = heroLocalEntity.attackType,
            roles = heroLocalEntity.roles,
            legs = heroLocalEntity.legs,
        )
    }
}