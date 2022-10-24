package com.voyager.weather.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voyager.design.R
import com.voyager.weather.domain.weather.WeatherData
import com.voyager.weather.domain.weather.WeatherInfo
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    data: WeatherData,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Today ${
                    data.time.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    )
                }",
                modifier = Modifier.align(Alignment.End),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = data.weatherType.iconRes),
                contentDescription = data.weatherType.weatherDesc,
                modifier = Modifier.size(160.dp)
            )
            Text(
                text = "Today ${
                    data.temperatureCelsius
                }C",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = 50.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = data.weatherType.weatherDesc,
                fontSize = 20.sp,
                color = Color.White,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            WeatherDataDisplay(
                value = data.pressure.roundToInt(),
                unit = "hpa",
                icon = ImageVector.vectorResource(id = R.drawable.cloudy),
                textStyle = TextStyle(color = Color.White)
            )
            WeatherDataDisplay(
                value = data.humidity.roundToInt(),
                unit = "%",
                icon = ImageVector.vectorResource(id = R.drawable.cloudy),
                textStyle = TextStyle(color = Color.White)
            )
            WeatherDataDisplay(
                value = data.windSpeed.roundToInt(),
                unit = "km/h",
                icon = ImageVector.vectorResource(id = R.drawable.cloudy_night_3),
                textStyle = TextStyle(color = Color.White)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
