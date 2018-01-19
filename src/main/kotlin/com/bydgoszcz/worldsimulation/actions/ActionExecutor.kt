package com.bydgoszcz.worldsimulation.actions

import com.bydgoszcz.worldsimulation.worlds.World
import java.util.*

class ActionExecutor(private val world: World) {
    fun addNewMan(manIndex : Int, womanIndex : Int) {
        val random = Random()

        ReproductiveMan(world, world.peoples[manIndex], world.peoples[womanIndex])
    }
}