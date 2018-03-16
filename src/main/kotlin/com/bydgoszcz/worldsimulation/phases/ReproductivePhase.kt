package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.extensions.getCurrentCouple
import com.bydgoszcz.worldsimulation.extensions.lookForCouple
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.utils.copy
import com.bydgoszcz.worldsimulation.worlds.World

class ReproductivePhase : Phase("Reproductive phase") {
    override fun execute(world: World) {
        world.peoples.copy().forEach {
            analyzeRelationship(it, world)
        }
    }

    private fun analyzeRelationship(person: Person, world: World) {
        val pregnancy = world.science.pregnancy
        val pregnantEvent = pregnancy.findPregnantHistoryEvent(person)

        val coupleHistory = person.getCurrentCouple(world)

        if (coupleHistory == null) {
            person.lookForCouple(world)
            return
        }

        if (pregnantEvent == null) {
            if (pregnancy.isTimeToBePregnant(person, world)) {
                pregnancy.bePregnant(person, coupleHistory, world)
                return
            }
            return
        }

        if (pregnancy.isTimeForDelivery(pregnantEvent, world.random, world.time)) {
            pregnancy.giveBirthToChild(pregnantEvent, world)
        }
    }
}