package com.voager.location

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.voager.location.data.LocationResult
import com.voyager.permissions.MissingPermissionException
import com.voyager.permissions.PermissionManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class LocationDelegateImpl(
    private val locationClient: FusedLocationProviderClient, private val permissionManager: PermissionManager
) : LocationDelegate {

    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getLocation(): LocationResult {

        if (permissionManager.hasPermission(PermissionManager.Permission.Location).not()) {
            return LocationResult.Error(MissingPermissionException.MissingLocationPermissionException)
        }

        if (permissionManager.hasPermission(PermissionManager.Permission.PlayServices).not()) {
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
