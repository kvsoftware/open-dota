package com.kvsoftware.opendota.domain.usecase

import com.kvsoftware.opendota.data.repository.HeroesRepository
import com.kvsoftware.opendota.domain.model.HeroModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHeroesUseCase @Inject constructor(private val heroesRepository: HeroesRepository) {

    private val imageFullPath = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/heroes/"

    suspend operator fun invoke(apiKey: String): List<HeroModel> =
        withContext(Dispatchers.Default) {
            val heroEntities = heroesRepository.getHeroes(apiKey).sortedBy { it.name }
            val heroModels = arrayListOf<HeroModel>()
            for (heroEntity in heroEntities) {
                val name = heroEntity.name
                val fileName = name.replace("npc_dota_hero_", "") + ".png"
                heroModels.add(
                    HeroModel(
                        heroEntity.localizedName,
                        imageFullPath + fileName,
                        heroEntity.primaryAttr,
                        heroEntity.attackType,
                        heroEntity.roles.joinToString()
                    )
                )
            }
            heroModels
        }
}