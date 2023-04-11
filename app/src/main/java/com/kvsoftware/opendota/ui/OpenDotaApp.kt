package com.kvsoftware.opendota.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kvsoftware.opendota.R
import com.kvsoftware.opendota.ui.page.HeroesScreen

enum class OpenDotaScreen(@StringRes val title: Int) {
    Heroes(title = R.string.heroes_title),
    HeroDetail(title = R.string.hero_detail_title)
}

val defaultPage = OpenDotaScreen.Heroes.name

@Composable
fun OpenDotaApp() {
    OpenDotaNavHost()
}

@Composable
fun OpenDotaNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = OpenDotaScreen.valueOf(backStackEntry?.destination?.route ?: defaultPage)

    Scaffold(
        topBar = {
            OpenDotaAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = defaultPage,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(OpenDotaScreen.Heroes.name) {
                HeroesScreen(hiltViewModel())
            }
        }
    }
}

@Composable
fun OpenDotaAppBar(
    currentScreen: OpenDotaScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.global_back_button)
                    )
                }
            }
        }
    )
}

