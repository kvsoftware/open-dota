package com.kvsoftware.opendota.ui.page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvsoftware.opendota.domain.usecase.GetHeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(private val getHeroesUseCase: GetHeroesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<HeroesUiState>(HeroesUiState.Loading)
    val uiState: StateFlow<HeroesUiState> = _uiState.asStateFlow()

    fun getHeroes(apiKey: String) {
        viewModelScope.launch {
            val heroes = getHeroesUseCase.invoke(apiKey)
            _uiState.value = HeroesUiState.Success(heroes)
        }
    }
}