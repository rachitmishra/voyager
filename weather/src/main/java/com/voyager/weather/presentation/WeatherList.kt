package com.voyager.weather.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.voyager.weather.domain.weather.WeatherData
import kotlin.math.roundToInt

@Composable
fun WeatherList(
    dataPerDay: Map<Int, List<WeatherData>> = mapOf(), modifier: Modifier = Modifier
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            dataPerDay.flatMap { it.value }.take(7),
        ) { data ->
            WeatherDataDayDisplay(
                value = data.temperatureCelsius.roundToInt(),
                unit = "°C",
                icon = ImageVector.vectorResource(id = data.weatherType.iconRes),
                textStyle = TextStyle(color = Color.White)
            )
        }
    }
}
