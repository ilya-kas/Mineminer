package com.rubon.mineminer.screen.inventory.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubon.mineminer.R
import com.rubon.mineminer.logic.entity.Ore
import com.rubon.mineminer.screen.inventory.InventoryViewModel

class RecyclerAdapter(private val fragment: InventoryFragment, private val viewModel: InventoryViewModel) : RecyclerView.Adapter<RecyclerAdapter.LineViewHolder>(){

    /**
     * container of a line with itemView in it
     * itemView - LinearLayout generated from item_main.xml
     */
    class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivOre = itemView.findViewById<ImageView>(R.id.iv_ore)
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvCount = itemView.findViewById<TextView>(R.id.tv_count)
        val bSell = itemView.findViewById<Button>(R.id.b_sell)
    }

    //on create empty line
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_inventory, parent, false)
        return LineViewHolder(itemView)
    }

    //filling line views
    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val ore = Ore.values()[position]
        holder.ivOre.setImageResource(ore.image)
        holder.tvName.text = ore.name.lowercase()
        holder.tvCount.text = viewModel.player.inventory[ore]!!.toString()
        holder.bSell.setOnClickListener {
            if (viewModel.player.inventory[ore] == 0) return@setOnClickListener
            viewModel.player.inventory[ore] = viewModel.player.inventory[ore]!! - 1
            holder.tvCount.text = viewModel.player.inventory[ore]!!.toString()

            fragment.sellOre(ore)
        }
    }

    override fun getItemCount() = Ore.values().size
}