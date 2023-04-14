package com.kvsoftware.opendota.domain.usecase

import com.kvsoftware.opendota.data.entity.FavoriteEntity
import com.kvsoftware.opendota.data.repository.FavoriteRepository
import com.kvsoftware.opendota.data.repository.HeroesRepository
import com.kvsoftware.opendota.domain.mapper.toHeroDetailModel
import com.kvsoftware.opendota.domain.model.HeroDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetFavoriteHeroUseCase @Inject constructor(
    private val heroesRepository: HeroesRepository,
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id: Int, isFavorited: Boolean): HeroDetailModel? =
        withContext(Dispatchers.Default) {
            if (isFavorited) {
                favoriteRepository.insert(FavoriteEntity(id))
            } else {
                favoriteRepository.deleteById(id)
            }
            val favoriteEntity = favoriteRepository.getFavoriteById(id)
            val heroEntity = heroesRepository.getHeroById(id)
            heroEntity?.toHeroDetailModel(favoriteEntity != null)
        }
}
