package com.voyager.ui

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class PermissionDelegate(private val activity: ComponentActivity) {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private fun checkLocationPermission(onResult: () -> Unit) {
        permissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            onResult()
        }
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}
