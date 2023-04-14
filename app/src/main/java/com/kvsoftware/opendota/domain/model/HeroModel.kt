package com.kvsoftware.opendota.domain.model

data class HeroModel(
    val id: Int,
    val name: String,
    val imagePath: String? = null,
    val primaryAttr: String,
    val attackType: String,
    val roles: String
)