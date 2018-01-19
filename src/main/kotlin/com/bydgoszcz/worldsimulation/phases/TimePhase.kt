package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.worlds.World

class TimePhase : Phase("Time phase") {
    override fun execute(world: World) {
        //log.d("Time elapsing, current time: " + world.time)
        world.time++
    }
}