package com.kvsoftware.opendota.data.datasource

import com.kvsoftware.opendota.data.entity.HeroEntity
import javax.inject.Inject

class HeroesLocalDataSource @Inject constructor() {
    private val myHeroEntities = arrayListOf<HeroEntity>()

    suspend fun getHeroes(): List<HeroEntity> {
        return myHeroEntities
    }

    suspend fun updateHeroes(heroEntities: List<HeroEntity>) {
        myHeroEntities.clear()
        myHeroEntities.addAll(heroEntities)
    }
}