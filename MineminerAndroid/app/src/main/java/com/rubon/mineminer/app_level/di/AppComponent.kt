package com.rubon.mineminer.app_level.di

import com.rubon.mineminer.app_level.MainActivity
import com.rubon.mineminer.app_level.di.module.AppModule
import com.rubon.mineminer.app_level.di.module.BindModule
import com.rubon.mineminer.app_level.di.module.DBModule
import com.rubon.mineminer.screen.game.GameFragment
import com.rubon.mineminer.screen.inventory.view.InventoryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BindModule::class, AppModule::class, DBModule::class])
interface AppComponent {
    fun inject(fragment: GameFragment)
    fun inject(fragment: InventoryFragment)
    fun inject(activity: MainActivity)
}