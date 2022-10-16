package com.voager.location.data

sealed class LocationResult(val lat: Double, val lang: Double) {
    class Success(lat: Double, lang: Double) : LocationResult(lat, lang)
    class Error(val error: Exception = Exception("Couldn't fetch location.")) : LocationResult(0.0, 0.0)
}
