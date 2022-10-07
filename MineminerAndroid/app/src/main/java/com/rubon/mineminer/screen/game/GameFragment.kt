package com.rubon.mineminer.screen.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.rubon.mineminer.R
import com.rubon.mineminer.app_level.App
import com.rubon.mineminer.app_level.di.module.AppModule.Companion.activity
import com.rubon.mineminer.app_level.di.module.AppModule.Companion.unityPlayer
import com.rubon.mineminer.data.unity.UnityCommunicator
import com.rubon.mineminer.logic.entity.Instrument
import com.rubon.mineminer.screen.inventory.view.InventoryFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class GameFragment {
    @Inject
    lateinit var viewModel: GameViewModel
    @Inject
    lateinit var communicator: UnityCommunicator

    fun init(){
        App.appComponent.inject(this)
        LayoutInflater.from(activity).inflate(R.layout.fragment_game, activity.gameContainer)

        val unityContainer = activity.findViewById<FrameLayout>(R.id.unity_container)
        unityContainer.removeAllViews()
        unityContainer.addView(unityPlayer.view) //add unity

        val llInstruments = activity.findViewById<LinearLayout>(R.id.ll_instruments) //hide hud until loaded
        val bBackpack = activity.findViewById<ImageView>(R.id.iv_backpack)
        llInstruments.visibility = View.INVISIBLE
        bBackpack.visibility = View.INVISIBLE
        val timer = Timer()
        timer.schedule(HUDDelay(listOf(llInstruments,bBackpack)),2200)

        val bPixar = activity.findViewById<ImageView>(R.id.iv_pixar)
        bPixar.setBackgroundColor(activity.getColor(R.color.active))
        val bShovel = activity.findViewById<ImageView>(R.id.iv_shovel)

        bPixar.setOnClickListener {
            viewModel.selectedInstrument = Instrument.PIXAR
            bPixar.setBackgroundColor(activity.getColor(R.color.active))
            bShovel.setBackgroundColor(activity.getColor(R.color.inactive))
        }
        bShovel.setOnClickListener {
            viewModel.selectedInstrument = Instrument.SHOVEL
            bPixar.setBackgroundColor(activity.getColor(R.color.inactive))
            bShovel.setBackgroundColor(activity.getColor(R.color.active))
        }

        bBackpack.setOnClickListener {
            openInventory()
        }
    }

    fun show(){
        App.appComponent.inject(this)
        activity.gameContainer.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        activity.inventoryContainer.visibility = View.INVISIBLE
        activity.gameContainer.visibility = View.VISIBLE
    }

    private fun openInventory(){
        activity.isGame = false
        InventoryFragment().show()
    }
}

private class HUDDelay(val views: List<View>): TimerTask() {
    override fun run() {
        GlobalScope.launch (Dispatchers.Main){
            for (view in views)
                view.visibility = View.VISIBLE
        }
    }
}