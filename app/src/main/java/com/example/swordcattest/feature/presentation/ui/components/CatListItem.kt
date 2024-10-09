package com.example.swordcattest.feature.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.swordcattest.R
import com.example.swordcattest.feature.domain.model.CatItem

@Composable
fun CatListItem(
    cat: CatItem,
    shouldShowLifespan: Boolean,
    onImageClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(10.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(
                    LocalContext.current
                ).data(cat.url).crossfade(true).build(),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically)
                    .clickable {
                        onImageClick()
                    },
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_launcher_background)
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp),
                    text = cat.breeds.first().name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                val favoriteImage = if (cat.favourite) {
                    R.drawable.baseline_favorite_24
                } else {
                    R.drawable.baseline_favorite_border_24
                }

                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(30.dp)
                        .clickable {
                            onFavoritesClick()
                        },
                    painter = painterResource(favoriteImage),
                    contentDescription = stringResource(id = R.string.app_name)
                )

                if(shouldShowLifespan) {
                    val filteredLifespans = cat.breeds.mapNotNull { breed ->
                        breed.life_span.split(" ")
                            .firstOrNull()
                            ?.toDoubleOrNull()
                            ?.toInt()
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(10.dp),
                        text = "Average Lifespan: ${filteredLifespans.average().toInt()}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CatListItemPreview() {
}