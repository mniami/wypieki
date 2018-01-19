package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.Market
import com.bydgoszcz.worldsimulation.worlds.World
import java.util.*

class AnalysePhase : Phase("Analyse") {
    override fun execute(world: World) {
        val random = Random()

        world.lastRandom = random.nextInt(1000)
//        when(world.lastRandom){
//            in 1..200 -> {
//                log.d("new person created")
//                world.peoples.add(Person("p" + world.lastRandom))
//            }
//            in 201..220 -> {
//                log.d("new market created")
//                world.markets.add(Market("m" + world.lastRandom))
//            }
//        }
    }
}