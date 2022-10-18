package com.voager.location

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.voager.location.data.LocationResult
import com.voyager.permissions.MissingPermissionException
import com.voyager.permissions.PermissionDelegate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class LocationDelegateImpl(
    app: Application,
    private val permissionDelegate: PermissionDelegate,
) : LocationDelegate {

    private val locationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(app)

    private val locationManager = app.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private fun LocationManager.isGpsEnabled() =
        isProviderEnabled(NETWORK_PROVIDER) || isProviderEnabled(
            GPS_PROVIDER
        )

    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getLocation(): LocationResult {

        if (permissionDelegate.hasPermission(PermissionDelegate.Permission.Location).not()) {
            return LocationResult.Error(MissingPermissionException.MissingLocationPermissionException)
        }

        if (permissionDelegate.hasPermission(PermissionDelegate.Permission.PlayServices).not()) {
            return LocationResult.Error(MissingPermissionException.MissingLocationPermissionException)
        }

        if (locationManager.isGpsEnabled().not()) {
            return LocationResult.Error(MissingPermissionException.MissingLocationPermissionException)
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(result.toLocation()) {
                            LocationResult.Error().error
                        }
                    } else {
                        cont.resumeWithException(LocationResult.Error().error)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it.toLocation()) {
                        LocationResult.Error().error
                    }
                }
                addOnFailureListener {
                    cont.resumeWithException(LocationResult.Error().error)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}

fun android.location.Location?.toLocation(): LocationResult {
    this ?: return LocationResult.Error()

    return LocationResult.Success(latitude, longitude)
}
