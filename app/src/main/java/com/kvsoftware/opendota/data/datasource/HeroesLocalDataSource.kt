package com.kvsoftware.opendota.data.datasource

import com.kvsoftware.opendota.data.local.HeroDao
import com.kvsoftware.opendota.data.entity.HeroLocalEntity
import javax.inject.Inject

class HeroesLocalDataSource @Inject constructor(private val heroDao: HeroDao) {

    suspend fun getHeroById(id: Int): HeroLocalEntity? {
        return heroDao.getHeroById(id)
    }

    suspend fun getHeroes(): List<HeroLocalEntity> {
        return heroDao.getHeroes()
    }

    suspend fun updateHeroes(heroLocalEntities: List<HeroLocalEntity>) {
        heroDao.deleteHeroes()
        heroDao.insertAll(heroLocalEntities)
    }
}