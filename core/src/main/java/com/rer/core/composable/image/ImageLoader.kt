package com.rer.core.composable.image

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.rer.core.R

@Composable
fun ImageLoader(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.FillWidth
) {
    val context = LocalContext.current

    val imageUrl = url.ifBlank {
        R.drawable.profile_placeholder
    }

    SubcomposeAsyncImage(
        model = ImageRequest
            .Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
    )
}