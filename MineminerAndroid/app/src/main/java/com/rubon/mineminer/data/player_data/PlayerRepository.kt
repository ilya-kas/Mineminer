package com.rubon.mineminer.data.player_data

import com.rubon.mineminer.data.player_data.source.LoadablePlayer
import com.rubon.mineminer.data.player_data.source.PlayersDAO
import com.rubon.mineminer.logic.entity.Ore
import com.rubon.mineminer.logic.entity.Player
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepository @Inject constructor(private val dao: PlayersDAO) {
    lateinit var player: Player

    suspend fun loadPlayer(){
        try {
            player = dao.getPlayer().toPlayer()
        } catch (e: Exception){
            player = Player()
            for (ore in Ore.values())
                player.inventory[ore] = 0
        }
    }

    suspend fun savePlayer(){
        dao.insert(LoadablePlayer(player))
    }
}