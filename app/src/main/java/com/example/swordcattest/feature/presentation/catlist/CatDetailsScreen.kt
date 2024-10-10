package com.example.swordcattest.feature.presentation.catlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.swordcattest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailsScreen(catId: String, viewModel: CatViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp,50.dp,20.dp,20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),

            ) {

            val catItem = viewModel.getCatById(catId)

            var checked by remember { mutableStateOf(catItem?.favourite) }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = "isfavorite",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            )

            checked?.let { state ->
                Checkbox(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    checked = state,
                    onCheckedChange = {
                        checked = it
                        catItem?.favourite = it
                        catItem?.let { it1 -> viewModel.onFavoritesClick(it1) }
                    }
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(
                    LocalContext.current
                ).data(catItem?.url).crossfade(true).build(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_launcher_background)
            )

            catItem?.breeds?.first()?.name?.let {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Name: $it"
                )
            }

            catItem?.breeds?.first()?.origin?.let {
                Text(
                    modifier = Modifier.padding(8.dp), text = "Origin: $it"
                )
            }

            catItem?.breeds?.first()?.temperament?.let {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Temperament: $it"
                )
            }

            catItem?.breeds?.first()?.description?.let {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Description: $it"
                )
            }
        }
    }
}

