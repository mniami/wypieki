package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.items.GlobalParameters
import com.bydgoszcz.worldsimulation.worlds.World
import java.util.*

class AnalysePhase : Phase("Analyse") {
    val random = Random()

    override fun execute(world: World) {
        world.globalRandoms = GlobalParameters(random.nextGaussian(), random.nextGaussian())
    }
}