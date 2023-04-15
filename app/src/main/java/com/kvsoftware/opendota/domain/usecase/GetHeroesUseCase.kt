package com.kvsoftware.opendota.domain.usecase

import com.kvsoftware.opendota.data.repository.HeroesRepository
import com.kvsoftware.opendota.domain.mapper.toHeroModel
import com.kvsoftware.opendota.domain.model.HeroModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHeroesUseCase @Inject constructor(private val heroesRepository: HeroesRepository) {
    suspend operator fun invoke(apiKey: String): List<HeroModel> = withContext(Dispatchers.Default) {
        val heroEntities = heroesRepository.getHeroes(apiKey).sortedBy { it.name }
        heroEntities.map { it.toHeroModel() }
    }
}