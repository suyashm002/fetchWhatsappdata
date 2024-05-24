package com.example.waproject

import android.app.Application
import com.example.waproject.inject.appModule
import com.google.firebase.FirebaseApp
import io.realm.kotlin.Realm
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

public class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApplicationClass)
            modules(appModule)
        }
    }
}


