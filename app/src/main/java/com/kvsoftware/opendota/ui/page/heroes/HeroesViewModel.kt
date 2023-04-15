package com.kvsoftware.opendota.ui.page.heroes

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvsoftware.opendota.R
import com.kvsoftware.opendota.domain.model.HeroModel
import com.kvsoftware.opendota.domain.usecase.GetHeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(private val getHeroesUseCase: GetHeroesUseCase) : ViewModel() {

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
            _heroModels.emit(getHeroesUseCase.invoke(apiKey))
            _isLoading.emit(false)
        }
    }
}