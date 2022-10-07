package com.rubon.mineminer.app_level.di.module

import android.content.Context
import androidx.room.Room
import com.rubon.mineminer.data.player_data.source.PlayersDAO
import com.rubon.mineminer.data.player_data.Database
import dagger.Module
import dagger.Provides

@Module
open class DBModule(context: Context) {
    private val db = Room
        .databaseBuilder(context, Database::class.java, "App")
        .build()

    @Provides
    open fun getPlayersDAO(): PlayersDAO {
        return db.playersDAO()
    }
}