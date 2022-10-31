package com.voyager.movies.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.voyager.movies.domain.MovieItem

@Composable
fun MovieItem1(item: MovieItem) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(120.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(item.image).size(coil.size.Size.ORIGINAL)
                    .crossfade(true).build()
            ), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart
        ) {
            Surface(
                color = Color("#55111111".toColorInt()), modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title, modifier = Modifier.padding(8.dp), style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}
