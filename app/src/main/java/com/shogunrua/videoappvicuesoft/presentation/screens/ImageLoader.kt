package com.shogunrua.videoappvicuesoft.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageLoader(
    item: String
) {
    Image(
        painter = rememberAsyncImagePainter(item),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.size(80.dp)
    )
}
