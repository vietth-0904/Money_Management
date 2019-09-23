package com.sun.moneymanagement

import android.app.Application
import com.sun.moneymanagement.di.activityModule
import com.sun.moneymanagement.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PiggyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PiggyApplication)
            modules(listOf(appModule, activityModule))
        }
    }
}
