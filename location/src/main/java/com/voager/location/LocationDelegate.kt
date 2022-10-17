package com.voager.location

import com.voager.location.data.LocationResult
import com.voyager.core.di.VGDependency

interface LocationDelegate: VGDependency {
    suspend fun getLocation(): LocationResult
}
