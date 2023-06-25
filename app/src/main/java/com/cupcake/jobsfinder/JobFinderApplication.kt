package com.cupcake.jobsfinder

import android.app.Application
import com.cupcake.remote.interceptor.WifiService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JobFinderApplication : Application() {
    companion object {
        lateinit var instance: JobFinderApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupServices()
    }

    private fun setupServices() {
        WifiService.instance.initializeWithApplicationContext(this)
    }
}