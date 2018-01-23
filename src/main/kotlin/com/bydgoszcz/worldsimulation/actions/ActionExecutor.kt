package com.bydgoszcz.worldsimulation.actions

import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.worlds.World
import java.util.*

class ActionExecutor(private val world: World) {
    fun addNewMan(manIndex : Int, womanIndex : Int) {
        val random = Random()

        ReproductiveManAction(world, world.peoples[manIndex], world.peoples[womanIndex])
    }
    fun giveBirthToChild(child : Person){
        //TODO implement it
    }
}