package com.kvsoftware.opendota.data.mapper

import com.kvsoftware.opendota.data.entity.HeroEntity
import com.kvsoftware.opendota.data.entity.HeroLocalEntity
import com.kvsoftware.opendota.data.entity.HeroRemoteEntity

fun HeroRemoteEntity.toHeroLocalEntity(): HeroLocalEntity {
    return HeroLocalEntity(
        id = id,
        name = name,
        localizedName = localizedName,
        primaryAttr = primaryAttr,
        attackType = attackType,
        roles = roles,
        legs = legs
    )
}

fun HeroLocalEntity.toHeroEntity(): HeroEntity {
    return HeroEntity(
        id = id,
        name = name,
        localizedName = localizedName,
        primaryAttr = primaryAttr,
        attackType = attackType,
        roles = roles,
        legs = legs,
    )
}