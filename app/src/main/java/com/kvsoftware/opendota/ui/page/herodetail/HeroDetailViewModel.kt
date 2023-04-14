package com.kvsoftware.opendota.ui.page.herodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvsoftware.opendota.domain.model.HeroDetailModel
import com.kvsoftware.opendota.domain.usecase.GetHeroDetailByIdUseCase
import com.kvsoftware.opendota.domain.usecase.SetFavoriteHeroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val getHeroDetailByIdUseCase: GetHeroDetailByIdUseCase,
    private val setFavoriteHeroUseCase: SetFavoriteHeroUseCase,
) : ViewModel() {

    private var _heroDetailModel = MutableStateFlow<HeroDetailModel?>(null)
    val heroDetailModel: StateFlow<HeroDetailModel?> = _heroDetailModel.asStateFlow()
    fun getHeroById(id: Int) {
        viewModelScope.launch {
            _heroDetailModel.emit(getHeroDetailByIdUseCase.invoke(id))
        }
    }

    fun setFavorite() {
        viewModelScope.launch {
            val heroDetailModel = heroDetailModel.value ?: return@launch
            _heroDetailModel.emit(setFavoriteHeroUseCase.invoke(heroDetailModel.id, !heroDetailModel.isFavorited))
        }
    }
}