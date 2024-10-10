package com.example.swordcattest.feature_cat_listing.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.swordcattest.feature_cat_listing.domain.model.CatItem

@Composable
fun CatListItem(
    cat: CatItem,
    shouldShowLifespan: Boolean,
    onImageClick: () -> Unit,
    onFavoritesClick: (cat: CatItem) -> Unit,
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(0.8f),
                        text = cat.breeds.first().name,
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    var checked by remember { mutableStateOf(cat.favourite) }

                    Checkbox(
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(0.2f),
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                            cat.favourite = it
                            onFavoritesClick(cat)
                        }
                    )
                }

                if(shouldShowLifespan) {
                    val filteredLifespans = cat.breeds.mapNotNull { breed ->
                        breed.lifespan.split(" ")
                            .firstOrNull()
                            ?.toDoubleOrNull()
                            ?.toInt()
                    }

                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.catAverageLifespan) + "${
                            filteredLifespans.average().toInt()
                        }",
                        style = TextStyle(
                            fontSize = 18.sp,
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