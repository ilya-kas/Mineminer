package com.rubon.mineminer.data.player_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubon.mineminer.data.player_data.source.PlayersDAO
import com.rubon.mineminer.data.player_data.source.LoadablePlayer

@Database(entities = [LoadablePlayer::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun playersDAO(): PlayersDAO
}