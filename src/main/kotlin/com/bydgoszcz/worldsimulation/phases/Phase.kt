package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.utils.Log
import com.bydgoszcz.worldsimulation.worlds.World

/**
 * Each phase should execute actions
 */
open class Phase (val name: String, protected val log : Log = Log()){
    open fun execute(world: World) {

    }
}