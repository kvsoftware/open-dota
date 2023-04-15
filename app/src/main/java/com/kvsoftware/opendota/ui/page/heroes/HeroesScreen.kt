package com.kvsoftware.opendota.ui.page.heroes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kvsoftware.opendota.R
import com.kvsoftware.opendota.domain.model.HeroModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeroesScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HeroesViewModel>()
    val items: List<HeroModel> by viewModel.heroModels.collectAsStateWithLifecycle(listOf())
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(isLoading, { viewModel.getHeroes() })

    viewModel.getHeroes()

    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn {
            items(items) { heroModel ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    .clickable {
                        navController.navigate("hero/${heroModel.id}")
                    }) {
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = heroModel.imagePath,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .height(120.dp)
                                .width(120.dp),
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(heroModel.name, fontSize = 24.sp)
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(
            refreshing = isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}