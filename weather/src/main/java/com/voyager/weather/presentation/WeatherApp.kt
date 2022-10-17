package com.voyager.weather.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun WeatherApp(viewModel: WeatherViewModel) {
    val state = viewModel.state
    if (state.isLoading) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircularProgressIndicator()
        }
        return
    }
    val snackBarHostState = remember { SnackbarHostState() }
    if (state.error != null) {
        LaunchedEffect(snackBarHostState) {
            snackBarHostState.showSnackbar(
                message = state.error, actionLabel = "Retry."
            )
        }
    }
    return WeatherCard(state = state, backgroundColor = Color.Blue)
}




