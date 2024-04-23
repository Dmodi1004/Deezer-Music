package com.example.deezermusic

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeezerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}