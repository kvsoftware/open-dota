package com.kvsoftware.opendota.domain.usecase

import com.kvsoftware.opendota.data.repository.FavoriteRepository
import com.kvsoftware.opendota.data.repository.HeroesRepository
import com.kvsoftware.opendota.domain.mapper.toHeroModel
import com.kvsoftware.opendota.domain.model.HeroModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoritedHeroesUseCase @Inject constructor(
    private val heroesRepository: HeroesRepository,
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(apiKey: String): List<HeroModel> = withContext(Dispatchers.Default) {
        val favoritedHeroIds = favoriteRepository.getFavorites().map { it.id }
        val heroEntities = heroesRepository.getHeroes(apiKey).sortedBy { it.name }.filter { favoritedHeroIds.contains(it.id) }
        heroEntities.map { it.toHeroModel() }
    }
}