package com.kvsoftware.opendota.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero")
data class HeroLocalEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "localized_name") val localizedName: String,
    @ColumnInfo(name = "primary_attr") val primaryAttr: String,
    @ColumnInfo(name = "attack_type") val attackType: String,
    @ColumnInfo(name = "roles") val roles: List<String>,
    @ColumnInfo(name = "legs") val legs: String
)