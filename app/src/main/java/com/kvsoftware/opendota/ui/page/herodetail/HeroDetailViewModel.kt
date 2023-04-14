package com.kvsoftware.opendota.ui.page.herodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvsoftware.opendota.domain.model.HeroModel
import com.kvsoftware.opendota.domain.usecase.GetHeroByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(private val getHeroByIdUseCase: GetHeroByIdUseCase) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var _heroModel = MutableStateFlow<HeroModel?>(null)
    val heroModel: StateFlow<HeroModel?> = _heroModel.asStateFlow()

    fun getHeroById(id: Int) {
        viewModelScope.launch {
            _heroModel.emit(getHeroByIdUseCase.invoke(id))
            _isLoading.emit(false)
        }
    }
}