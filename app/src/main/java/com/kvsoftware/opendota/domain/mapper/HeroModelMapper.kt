package com.kvsoftware.opendota.domain.mapper

import com.kvsoftware.opendota.data.entity.HeroEntity
import com.kvsoftware.opendota.domain.model.HeroModel

fun HeroEntity.toHeroModel(): HeroModel {
    val imageFullPath = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/heroes/"
    val fileNamePrefix = "npc_dota_hero_"
    val fileType = ".png"
    return HeroModel(
        id = id,
        name = localizedName,
        imagePath = imageFullPath + name.replace(fileNamePrefix, "") + fileType,
    )
}