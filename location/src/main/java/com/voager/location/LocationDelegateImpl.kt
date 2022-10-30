package com.voager.location

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.voager.location.data.LocationResult
import com.voyager.permissions.PermissionDelegate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class LocationDelegateImpl(
    app: Application,
    private val permissionDelegate: PermissionDelegate,
) : LocationDelegate {

    private val locationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(app)

    private val locationManager = app.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private fun LocationManager.isGpsEnabled() = isProviderEnabled(NETWORK_PROVIDER) || isProviderEnabled(
        GPS_PROVIDER
    )

    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getLocation(): LocationResult {

        if (permissionDelegate.hasPermission(PermissionDelegate.Permission.Location).not()) {
            Log.w("LocationDelegateImpl", "Location permission is not granted")
            return LocationResult.Error(com.voyager.permissions.MissingPermissionException.MissingLocationPermissionException)
        }

        if (permissionDelegate.hasPermission(PermissionDelegate.Permission.PlayServices).not()) {
            Log.w("LocationDelegateImpl", "Play services not available")
            return LocationResult.Error(com.voyager.permissions.MissingPermissionException.MissingLocationPermissionException)
        }

        if (locationManager.isGpsEnabled().not()) {
            Log.w("LocationDelegateImpl", "GPS is not enabled")
            return LocationResult.Error(com.voyager.permissions.MissingPermissionException.MissingLocationPermissionException)
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        Log.w("LocationDelegateImpl", "Location is successful")
                        cont.resume(result.toLocation()) {
                            LocationResult.Error().error
                        }
                    } else {
                        Log.w("LocationDelegate", "Failed to get location", exception)
                        cont.resumeWithException(LocationResult.Error().error)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    //Log.w("LocationDelegateImpl", "Location is successful ${it.latitude}")
                    cont.resume(it.toLocation()) {
                        LocationResult.Error().error
                    }
                }
                addOnFailureListener {
                    Log.w("LocationDelegate", "Failed to get location", it)
                    cont.resumeWithException(LocationResult.Error().error)
                }
                addOnCanceledListener {
                    Log.w("LocationDelegate", "Failed to get location")
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
