package com.kvsoftware.opendota.ui.page.herodetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImage
import com.kvsoftware.opendota.R

@Composable
fun HeroDetailScreen(id: Int) {
    val viewModel = hiltViewModel<HeroDetailViewModel>()
    val heroDetailModel by viewModel.heroDetailModel.collectAsStateWithLifecycle()
    viewModel.getHeroById(id)
    Column(
        modifier = Modifier
            .fillMaxWidth(1F)
            .padding(all = 8.dp),
    ) {
        if (heroDetailModel != null) {
            AsyncImage(
                model = heroDetailModel!!.imagePath,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxHeight(.3F)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = heroDetailModel!!.name,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(1F)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.heroes_primary_attr) + heroDetailModel!!.primaryAttr, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.heroes_attack_type) + heroDetailModel!!.attackType, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(stringResource(R.string.heroes_roles) + heroDetailModel!!.roles, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { viewModel.setFavorite() },
            ) {
                // Inner content including an icon and a text label
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    tint = if (heroDetailModel!!.isFavorited) Color.Red else Color.White
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Like")
            }
        }
    }
}