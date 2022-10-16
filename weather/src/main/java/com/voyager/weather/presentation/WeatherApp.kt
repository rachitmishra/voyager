package com.voyager.weather.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun WeatherApp(viewModel: WeatherViewModel) {
    WeatherCard(state = viewModel.state, backgroundColor = Color.Blue)
}



