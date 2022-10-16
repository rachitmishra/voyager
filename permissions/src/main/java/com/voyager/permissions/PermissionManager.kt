package com.voyager.permissions

import android.content.Context

interface PermissionManager {
    enum class Permission {
        Location,
        PlayServices
    }

    fun hasPermission(permission: Permission): Boolean

    fun requestPermission(context: Context, permission: Permission)
}
