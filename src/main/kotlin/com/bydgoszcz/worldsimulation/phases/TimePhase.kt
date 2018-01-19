package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.worlds.MarketWorld

class TimePhase : Phase("Time phase") {
    override fun execute(world: MarketWorld) {
        //log.d("Time elapsing, current time: " + world.time)
        world.time++
    }
}