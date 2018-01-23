package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.actions.CreateManAction
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.worlds.World

class GodPhase : Phase("God phase") {
    private var peopleCreation = 0

    override fun execute(world: World) {
        super.execute(world)

        if (world.peoples.size == 0) {
            world.markets.clear()
            world.items.clear()

            if (world.time.days() < 100) {
                CreateManAction(world, Person("Adam", sex = SexType.MALE, mom = null, dad = null, bearthDay = world.time))
                CreateManAction(world, Person("Ewa", sex = SexType.FEMALE, mom = null, dad = null, bearthDay = world.time))
            }
            peopleCreation++
        }
    }
}