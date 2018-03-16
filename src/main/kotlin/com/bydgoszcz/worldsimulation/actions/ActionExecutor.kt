package com.bydgoszcz.worldsimulation.actions

import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.worlds.World
import java.util.*

class ActionExecutor(private val world: World) {
    fun addNewMan(manIndex : Int, womanIndex : Int) {
        ReproductiveManAction(world.peoples[manIndex], world.peoples[womanIndex]).addNewPersonToWorld(world)
    }
    fun giveBirthToChild(child : Person){
        //TODO implement it
    }
}