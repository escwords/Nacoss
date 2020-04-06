package com.words.nacoss

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import timber.log.Timber

class NacossApplication : Application() {


    //this method is run on app launch
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
    //here we fix the index memory compile time error message
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
