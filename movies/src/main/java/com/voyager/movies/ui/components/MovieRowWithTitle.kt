package com.voyager.movies.ui.components

import LoadingItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.voyager.movies.domain.MovieItem
import com.voyager.movies.ui.state.PageError

@Composable
fun MovieRowWithTitle(
    title: String,
    trending: List<MovieItem>,
    isLoading: Boolean,
    error: PageError? = null,
    onEndReachedPopularMovies: (() -> Unit)? = null,
    itemType: Int = 0,
) {
    if (error != null) {
        ErrorBox(msg = error.msg)
    }
    val lastIndex = trending.lastIndex
    Row(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(4.dp)
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(4.dp, 12.dp)) {
                itemsIndexed(trending) { index, item ->
                    if (index == lastIndex) {
                        onEndReachedPopularMovies?.invoke()
                    }
                    when (itemType) {
                        0 -> MovieItem(item)
                        1 -> MovieItem1(item)
                    }
                }
            }
        }
        if (isLoading) {
            LoadingItem()
        }
    }
}
