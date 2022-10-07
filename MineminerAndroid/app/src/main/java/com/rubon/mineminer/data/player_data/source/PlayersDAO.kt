package com.rubon.mineminer.data.player_data.source

import androidx.room.*
import com.rubon.mineminer.data.player_data.source.LoadablePlayer

@Dao
interface PlayersDAO {
    @Query("SELECT * FROM players LIMIT 1")
    fun getPlayer(): LoadablePlayer

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: LoadablePlayer)
}