package com.rubon.mineminer.app_level

import android.os.Bundle
import android.widget.FrameLayout
import com.rubon.mineminer.R
import com.rubon.mineminer.app_level.di.module.AppModule
import com.rubon.mineminer.data.player_data.PlayerRepository
import com.rubon.mineminer.screen.game.GameFragment
import com.rubon.mineminer.screen.inventory.view.InventoryFragment
import com.unity3d.player.UnityPlayerActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : UnityPlayerActivity() {
    var isGame = true
    lateinit var gameContainer: FrameLayout
    lateinit var inventoryContainer: FrameLayout

    @Inject
    lateinit var playerRepository: PlayerRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)

        gameContainer = findViewById(R.id.game_container)
        inventoryContainer = findViewById(R.id.inventory_container)

        AppModule.activity = this
        AppModule.unityPlayer = mUnityPlayer

        GlobalScope.launch {
            playerRepository.loadPlayer()
            GlobalScope.launch (Dispatchers.Main) {
                GameFragment().init()
                InventoryFragment().init()

                GameFragment().show()
            }
        }
    }

    override fun onBackPressed() {
        if (isGame)
            super.onBackPressed()
        else {
            isGame = true
            GameFragment().show()
        }
    }
}