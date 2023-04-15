package com.kvsoftware.opendota.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kvsoftware.opendota.data.entity.HeroLocalEntity

@Dao
interface HeroDao {

    @Query("SELECT * FROM hero WHERE id=:id")
    suspend fun getHeroById(id: Int): HeroLocalEntity?

    @Query("SELECT * FROM hero")
    suspend fun getHeroes(): List<HeroLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(heroLocalEntities: List<HeroLocalEntity>)

    @Query("DELETE FROM hero")
    suspend fun deleteHeroes()
}