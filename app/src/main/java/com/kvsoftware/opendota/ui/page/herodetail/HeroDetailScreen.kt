package com.kvsoftware.opendota.ui.page.herodetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry

@Composable
fun HeroDetailScreen(id: Int) {
    val viewModel = hiltViewModel<HeroDetailViewModel>()
    viewModel.getHeroById(id)
    Column() {
        Text(text = "test")
    }
}