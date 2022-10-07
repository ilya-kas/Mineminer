package com.rubon.mineminer.screen.inventory

import com.rubon.mineminer.data.player_data.PlayerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InventoryViewModel @Inject constructor(private val playerRepository: PlayerRepository) {
    val player = playerRepository.player

    suspend fun save(){
        playerRepository.savePlayer()
    }
}