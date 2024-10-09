package com.example.swordcattest.feature.presentation.catlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.swordcattest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailsScreen(catId: String, viewModel: CatViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            val catItem = viewModel.getCatById(catId)

            AsyncImage(
                model = ImageRequest.Builder(
                    LocalContext.current
                ).data(catItem?.url).crossfade(true).build(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 50.dp, 0.dp, 0.dp),
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