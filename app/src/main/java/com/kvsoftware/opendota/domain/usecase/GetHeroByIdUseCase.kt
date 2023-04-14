package com.kvsoftware.opendota.domain.usecase

import com.kvsoftware.opendota.data.repository.HeroesRepository
import com.kvsoftware.opendota.domain.model.HeroModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHeroByIdUseCase @Inject constructor(private val heroesRepository: HeroesRepository) {

    private val imageFullPath = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/heroes/"
    private val fileNamePrefix = "npc_dota_hero_"
    private val fileType = ".png"

    suspend operator fun invoke(id: Int): HeroModel? =
        withContext(Dispatchers.Default) {
            val heroEntity = heroesRepository.getHeroById(id)
            if (heroEntity == null) {
                null
            }

            val name = heroEntity!!.name
            val fileName = name.replace(fileNamePrefix, "") + fileType
            HeroModel(
                id = heroEntity.id,
                name = heroEntity.localizedName,
                imagePath = imageFullPath + fileName,
                primaryAttr = heroEntity.primaryAttr,
                attackType = heroEntity.attackType,
                roles = heroEntity.roles.joinToString()
            )
        }
}
