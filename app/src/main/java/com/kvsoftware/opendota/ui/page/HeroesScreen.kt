package com.kvsoftware.opendota.ui.page

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.kvsoftware.opendota.R
import com.kvsoftware.opendota.domain.model.HeroModel

@Composable
fun HeroesScreen(viewModel: HeroesViewModel) {
    viewModel.getHeroes(stringResource(R.string.api_key))
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        HeroesUiState.Loading -> Loading()
        is HeroesUiState.Success -> HeroesList((uiState as HeroesUiState.Success).heroModels)
    }
}

@Composable
fun Loading() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
fun HeroesList(heroModels: List<HeroModel>) {
    LazyColumn {
        items(heroModels) { heroModel ->
            val context = LocalContext.current
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    .clickable {
                        Toast
                            .makeText(context, "Card", Toast.LENGTH_SHORT)
                            .show()
                    }
            ) {
                Row {
                    AsyncImage(
                        model = heroModel.imagePath,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp),
                    )
                    Column(modifier = Modifier.padding(all = 8.dp)) {
                        Text(heroModel.name)
                        Text(stringResource(R.string.heroes_primary_attr) + heroModel.primaryAttr, fontSize = 14.sp)
                        Text(stringResource(R.string.heroes_attack_type) + heroModel.attackType, fontSize = 14.sp)
                        Text(stringResource(R.string.heroes_roles) + heroModel.roles, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

