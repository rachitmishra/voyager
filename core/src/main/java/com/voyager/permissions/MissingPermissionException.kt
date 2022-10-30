package com.voyager.permissions

sealed class MissingPermissionException : Exception() {
    object MissingLocationPermissionException : MissingPermissionException()
    object MissingPlayServicesException : MissingPermissionException()
}
