package com.kvsoftware.opendota.data.datasource

import com.kvsoftware.opendota.data.entity.HeroRemoteEntity
import com.kvsoftware.opendota.data.remote.HeroApi
import javax.inject.Inject

class HeroesRemoteDataSource @Inject constructor(private val heroApi: HeroApi) {
    suspend fun getHeroes(): List<HeroRemoteEntity> {
        return heroApi.getHeroes()
    }
}