package com.example.waproject.eventBus

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object NotificationEventBus {
    val _notificationData =  MutableStateFlow<String?>(null)
    val notificationData : StateFlow<String?> = _notificationData

    fun sendNotificationData(notificationData: String?){
        _notificationData.value = notificationData
    }
}