package com.kvsoftware.opendota.data.remote

import com.kvsoftware.opendota.data.entity.HeroRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroApi {
    @GET("heroes")
    suspend fun getHeroes(@Query("api_key") apiKey: String? = null): List<HeroRemoteEntity>
}