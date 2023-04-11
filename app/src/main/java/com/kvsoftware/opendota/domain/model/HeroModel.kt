package com.kvsoftware.opendota.domain.model

data class HeroModel(
    val name: String,
    val imagePath: String,
    val primaryAttr: String,
    val attackType: String,
    val roles: String
)