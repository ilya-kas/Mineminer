package com.rubon.mineminer.data.unity

import com.rubon.mineminer.AndroidUnityCommunicator
import com.rubon.mineminer.logic.entity.Ore
import com.rubon.mineminer.screen.game.GameViewModel
import javax.inject.Inject

class UnityCommunicator @Inject constructor(private val viewModel: GameViewModel): AndroidUnityCommunicator() {
    override fun canFarmBlock(block: String): Boolean {
        val ore = Ore.valueOf(block)
        val result = ore.instrument == viewModel.selectedInstrument
        if (result)
            viewModel.player.inventory[ore] = viewModel.player.inventory[ore]!! + 1
        return result
    }
}