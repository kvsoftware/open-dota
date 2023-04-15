package com.kvsoftware.opendota.domain.mapper

import com.kvsoftware.opendota.data.entity.HeroEntity
import com.kvsoftware.opendota.domain.model.HeroDetailModel

fun HeroEntity.toHeroDetailModel(isFavorited: Boolean): HeroDetailModel {
    val imageFullPath = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/heroes/"
    val fileNamePrefix = "npc_dota_hero_"
    val fileType = ".png"
    return HeroDetailModel(
        id = id,
        name = localizedName,
        imagePath = imageFullPath + name.replace(fileNamePrefix, "") + fileType,
        primaryAttr = primaryAttr,
        attackType = attackType,
        roles = roles.joinToString(),
        isFavorited = isFavorited
    )
}