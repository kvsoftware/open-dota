package com.kvsoftware.opendota.data

import com.kvsoftware.opendota.data.entity.HeroEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenDotaService {
    @GET("heroes")
    suspend fun getHeroes(@Query("api_key") apiKey: String? = null): List<HeroEntity>
}