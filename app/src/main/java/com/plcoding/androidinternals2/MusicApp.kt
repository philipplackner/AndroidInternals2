package com.plcoding.androidinternals2

import android.app.Application
import com.plcoding.androidinternals2.service.MusicServiceController

class MusicApp: Application() {

    lateinit var musicServiceController: MusicServiceController

    override fun onCreate() {
        super.onCreate()
        musicServiceController = MusicServiceController(this)
    }
}