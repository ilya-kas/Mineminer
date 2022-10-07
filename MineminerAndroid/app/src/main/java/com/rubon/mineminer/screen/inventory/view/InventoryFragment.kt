package com.rubon.mineminer.screen.inventory.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rubon.mineminer.R
import com.rubon.mineminer.app_level.App
import com.rubon.mineminer.app_level.di.module.AppModule.Companion.activity
import com.rubon.mineminer.logic.entity.Ore
import com.rubon.mineminer.screen.inventory.InventoryViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class InventoryFragment {
    @Inject
    lateinit var viewModel: InventoryViewModel

    fun init(){
        App.appComponent.inject(this)
        LayoutInflater.from(activity).inflate(R.layout.fragment_inventory, activity.inventoryContainer)

        val bReturn = activity.findViewById<ImageView>(R.id.iv_return)
        bReturn.setOnClickListener {
            GlobalScope.launch {
                viewModel.save()
            }
            activity.onBackPressed()
        }

        recyclerView = activity.findViewById(R.id.rv_inventory)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = RecyclerAdapter(this, viewModel)
    }

    fun show(){
        App.appComponent.inject(this)
        activity.gameContainer.layoutParams = ViewGroup.LayoutParams(300,600)
        activity.gameContainer.visibility = View.INVISIBLE
        activity.inventoryContainer.visibility = View.VISIBLE

        recyclerView.adapter = RecyclerAdapter(this, viewModel)
        val tvEmeralds = activity.inventoryContainer.findViewById<TextView>(R.id.tv_emeralds)
        tvEmeralds.text = viewModel.player.emeralds.toString()
    }

    fun sellOre(ore: Ore){
        viewModel.player.emeralds += ore.price
        val tvEmeralds = activity.inventoryContainer.findViewById<TextView>(R.id.tv_emeralds)
        tvEmeralds.text = viewModel.player.emeralds.toString()
    }

    companion object{
        lateinit var recyclerView: RecyclerView
    }
}