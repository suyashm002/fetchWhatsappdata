package com.example.waproject.utils

import android.content.ComponentName
import android.content.ContentResolver
import android.content.Context
import android.provider.Settings

fun isNotificationListenerEnabled(context: Context, packageName: String): Boolean {
    val contentResolver: ContentResolver = context.contentResolver

    // Retrieve the enabled notification listeners
    val enabledListeners = Settings.Secure.getString(
        contentResolver,
        Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
    )

    return enabledListeners != null && enabledListeners.contains(packageName)
    // Check if the specified package is in the list of enabled notification listeners

}