package com.rubon.mineminer.screen.game

import com.rubon.mineminer.data.player_data.PlayerRepository
import com.rubon.mineminer.data.unity.UnityCommunicator
import com.rubon.mineminer.logic.entity.Instrument
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameViewModel @Inject constructor(playerRepository: PlayerRepository) {
    var selectedInstrument = Instrument.PIXAR
    val player = playerRepository.player
}