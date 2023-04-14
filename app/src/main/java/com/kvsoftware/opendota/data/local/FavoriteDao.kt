package com.kvsoftware.opendota.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kvsoftware.opendota.data.entity.FavoriteEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE id=:id")
    suspend fun getFavoriteById(id: Int): FavoriteEntity?

    @Query("SELECT * FROM favorite")
    suspend fun getFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun deleteById(id: Int)
}