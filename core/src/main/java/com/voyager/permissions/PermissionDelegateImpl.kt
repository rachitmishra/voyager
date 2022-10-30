package com.voyager.permissions

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class PermissionDelegateImpl(private val application: Application) : PermissionDelegate {


    override fun hasPermission(permission: PermissionDelegate.Permission): Boolean {
        if (permission == PermissionDelegate.Permission.Location) {
            return checkLocationPermission()
        }

        if (permission == PermissionDelegate.Permission.PlayServices) {
            return checkPlayServices()
        }

        return false
    }

    override fun requestPermission(context: Context, permission: PermissionDelegate.Permission) {

        if (permission == PermissionDelegate.Permission.PlayServices) {
            return requestPlayServicesInstall(context)
        }
    }

    private fun requestPlayServicesInstall(context: Context) {
        val availability = GoogleApiAvailability.getInstance()
        val resultCode = availability.isGooglePlayServicesAvailable(context)
        val activity = context as? AppCompatActivity ?: return
        availability.getErrorDialog(activity, resultCode, 0)?.show()
    }

    private fun checkLocationPermission(): Boolean {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )
        if (hasAccessFineLocationPermission.not() || hasCoarseLocationPermission.not() || isGpsEnabled.not()) {
            return false
        }

        return true
    }

    private fun checkPlayServices(): Boolean {
        val availability = GoogleApiAvailability.getInstance()
        val resultCode = availability.isGooglePlayServicesAvailable(application)
        return resultCode == ConnectionResult.SUCCESS
    }
}
