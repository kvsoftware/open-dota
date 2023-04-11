package com.kvsoftware.opendota.data.datasource

import com.kvsoftware.opendota.data.entity.HeroEntity
import com.kvsoftware.opendota.data.OpenDotaService
import com.kvsoftware.opendota.data.RestClient
import javax.inject.Inject

class HeroesRemoteDataSource @Inject constructor(private val restClient: RestClient) {
    suspend fun getHeroes(apiKey: String): List<HeroEntity> {
        return restClient.createService(OpenDotaService::class.java).getHeroes(apiKey = apiKey)
    }
}