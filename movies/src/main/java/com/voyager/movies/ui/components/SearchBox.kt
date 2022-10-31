package com.voyager.movies.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voyager.movies.domain.QueryItems

@ExperimentalMaterial3Api
@Composable
fun SearchBox(query: QueryItems, onQuery: (String) -> Unit) {
    TextField(
        value = query.query,
        onValueChange = onQuery,
        singleLine = true,
        placeholder = { Text("Find movies ...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(36.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.bodyMedium
    )
    if (query.movies.isNotEmpty()) {
        Box(
            modifier = Modifier.padding(8.dp, 12.dp)
        ) {
            MovieRowWithTitle("Search Results", query.movies, query.loading)
        }
    }
}
