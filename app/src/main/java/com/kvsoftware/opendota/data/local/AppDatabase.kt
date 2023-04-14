package com.kvsoftware.opendota.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kvsoftware.opendota.data.entity.HeroLocalEntity

@Database(entities = [HeroLocalEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}