package com.kvsoftware.opendota.ui.page.favoritedHeroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvsoftware.opendota.domain.model.HeroModel
import com.kvsoftware.opendota.domain.usecase.GetFavoritedHeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritedHeroesViewModel @Inject constructor(private val getFavoritedHeroesUseCase: GetFavoritedHeroesUseCase) :
    ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var _heroModels = MutableStateFlow<List<HeroModel>>(listOf())
    val heroModels: StateFlow<List<HeroModel>> = _heroModels.asStateFlow()

    private lateinit var apiKey: String

    fun init(apiKey: String) {
        this.apiKey = apiKey
    }

    fun getHeroes() {
        viewModelScope.launch {
            _heroModels.emit(getFavoritedHeroesUseCase.invoke(apiKey))
            _isLoading.emit(false)
        }
    }
}