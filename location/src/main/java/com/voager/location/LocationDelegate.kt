package com.voager.location

import com.voager.location.data.LocationResult

interface LocationDelegate {
    suspend fun getLocation(): LocationResult
}
