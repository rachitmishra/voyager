package com.voyager.weather.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WeatherApp(viewModel: WeatherViewModel) {
    val state by remember { viewModel.state }
    val snackBarHostState = remember { SnackbarHostState() }

    when (state) {
        is WeatherState.Error -> {
            LaunchedEffect(snackBarHostState) {
                snackBarHostState.showSnackbar(
                    message = (state as WeatherState.Error).msg, actionLabel = "Retry."
                )
            }
        }

        is WeatherState.Loaded -> {
            val dataForToday = (state as WeatherState.Loaded).weatherInfo.dataNow ?: return
            val dataPerDay = (state as WeatherState.Loaded).weatherInfo.dataPerDay
            Column(modifier = Modifier.fillMaxSize()) {
                WeatherCard(dataForToday, backgroundColor = Color.Blue)
                WeatherList(dataPerDay = dataPerDay)
            }
        }

        is WeatherState.Loading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
    }
}




