package com.rubon.mineminer.logic.entity

import com.rubon.mineminer.R

enum class Ore (val instrument: Instrument, val price: Int, val image: Int){
    IRON(Instrument.PIXAR, 2, R.drawable.iron),
    DIAMOND(Instrument.PIXAR, 4, R.drawable.diamond),
    GOLD(Instrument.PIXAR, 3, R.drawable.gold),
    DIRT(Instrument.SHOVEL, 1, R.drawable.dirt)
}