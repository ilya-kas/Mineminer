package com.rubon.mineminer.app_level.di.module

import com.rubon.mineminer.app_level.MainActivity
import com.unity3d.player.UnityPlayer
import dagger.Module

@Module
class AppModule {
    companion object{
        lateinit var activity: MainActivity
        lateinit var unityPlayer: UnityPlayer
    }
}