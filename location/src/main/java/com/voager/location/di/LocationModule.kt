package com.voager.location.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.voager.location.LocationDelegate
import com.voager.location.LocationDelegateImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Provides
    @Singleton
    fun provideFusedLocationProvider(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Binds
    @Singleton
    abstract fun bindLocationManager(
        locationManagerImpl: LocationDelegateImpl
    ): LocationDelegate
}
