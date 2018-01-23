package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.helpers.PregnancyHelper
import com.bydgoszcz.worldsimulation.worlds.World

class ReproductivePhase : Phase("Reproductive phase") {
    override fun execute(world: World) {
        val pregnancyHelper = PregnancyHelper()
        world.peoples.forEach {
            val pregnantEvent = pregnancyHelper.getCurrentPregnant(it)
            if (pregnantEvent != null &&
                    pregnancyHelper.isTimeForDelivery(pregnantEvent, world.random, world.time)) {
                //TODO move it to some action mechanism, as this should be noticed and log somewhere
                //TODO it is really important that new person is added to the world

                pregnancyHelper.giveBirthToChild(pregnantEvent, world)
            }
        }
    }
}