package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.worlds.World

class TimePhase : Phase("Time phase") {
    override fun execute(world: World) {
        val newTime = WorldTime(world.time.value())
        newTime.elapsed()
        world.time = newTime
    }
}