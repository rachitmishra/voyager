package com.voyager.weather.domain.weather

import androidx.annotation.DrawableRes
import com.voyager.design.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSky : WeatherType(
        "Clear Sky",
        iconRes = R.drawable.day
    )

    object MainlyClear : WeatherType(
        "Clear Sky",
        iconRes = R.drawable.cloudy_day_1
    )

    object PartlyCloudy : WeatherType(
        "Clear Sky",
        iconRes = R.drawable.cloudy_day_1
    )

    object Foggy : WeatherType(
        "Clear Sky",
        iconRes = R.drawable.cloudy_day_2
    )

    object Overcast : WeatherType(
        "Clear Sky",
        iconRes = R.drawable.cloudy_day_3
    )

    object SlightRain : WeatherType(
        "Clear Sky",
        iconRes = R.drawable.rainy_1
    )

    object SlightSnowFall : WeatherType(
        "Clear Sky",
        iconRes = R.drawable.snowy_1
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
//                48 -> DepositingRimeFog
//                51 -> LightDrizzle
//                53 -> ModerateDrizzle
//                55 -> DenseDrizzle
//                56 -> LightFreezingDrizzle
//                57 -> DenseFreezingDrizzle
                61 -> SlightRain
//                63 -> ModerateRain
//                65 -> HeavyRain
//                66 -> LightFreezingDrizzle
//                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
//                73 -> ModerateSnowFall
//                75 -> HeavySnowFall
//                77 -> SnowGrains
//                80 -> SlightRainShowers
//                81 -> ModerateRainShowers
//                82 -> ViolentRainShowers
//                85 -> SlightSnowShowers
//                86 -> HeavySnowShowers
//                95 -> ModerateThunderstorm
//                96 -> SlightHailThunderstorm
//                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}
