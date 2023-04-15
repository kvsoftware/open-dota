package com.kvsoftware.opendota.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(@PrimaryKey val id: Int)