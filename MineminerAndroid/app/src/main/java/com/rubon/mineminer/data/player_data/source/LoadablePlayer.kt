package com.rubon.mineminer.data.player_data.source

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rubon.mineminer.logic.entity.Ore
import com.rubon.mineminer.logic.entity.Player

@Entity(tableName = "players")
data class LoadablePlayer (
    @PrimaryKey
    val id: Int = 0,

    val emeralds: Int,
    val ores: String
){
    constructor(player: Player): this(emeralds = player.emeralds, ores = "")

    fun toPlayer(): Player{
        val player = Player()
        player.emeralds = emeralds
        for (ore in Ore.values())
            player.inventory[ore] = 0
        return player
    }
}