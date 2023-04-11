package com.kvsoftware.opendota.ui.page

import com.kvsoftware.opendota.domain.model.HeroModel

sealed interface HeroesUiState {
    object Loading : HeroesUiState
    data class Success(var heroModels: List<HeroModel>) : HeroesUiState
}