package com.kvsoftware.opendota.data.repository

import com.kvsoftware.opendota.data.datasource.FavoriteLocalDataSource
import com.kvsoftware.opendota.data.entity.FavoriteEntity
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteLocalDataSource: FavoriteLocalDataSource) {
    private val tag = "FavoriteRepository"

    suspend fun getFavoriteById(id: Int): FavoriteEntity? {
        return favoriteLocalDataSource.getFavoriteById(id)
    }

    suspend fun getFavorites(): List<FavoriteEntity> {
        return favoriteLocalDataSource.getFavorites()
    }

    suspend fun insert(favoriteEntity: FavoriteEntity) {
        favoriteLocalDataSource.insert(favoriteEntity)
    }

    suspend fun deleteById(id: Int) {
        favoriteLocalDataSource.deleteById(id)
    }

}