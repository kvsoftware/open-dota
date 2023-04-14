package com.kvsoftware.opendota.data.datasource

import com.kvsoftware.opendota.data.entity.FavoriteEntity
import com.kvsoftware.opendota.data.local.FavoriteDao
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(private val favoriteDao: FavoriteDao) {

    suspend fun getFavoriteById(id: Int): FavoriteEntity? {
        return favoriteDao.getFavoriteById(id)
    }

    suspend fun getFavorites(): List<FavoriteEntity> {
        return favoriteDao.getFavorites()
    }

    suspend fun insert(favoriteEntity: FavoriteEntity) {
        favoriteDao.insert(favoriteEntity)
    }

    suspend fun deleteById(id: Int) {
        favoriteDao.deleteById(id)
    }

}