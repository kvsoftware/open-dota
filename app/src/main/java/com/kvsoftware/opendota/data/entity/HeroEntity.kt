package com.kvsoftware.opendota.data.entity

data class HeroEntity(
    val id: Int,
    val name: String,
    val localizedName: String,
    val primaryAttr: String,
    val attackType: String,
    val roles: List<String>,
    val legs: String
)