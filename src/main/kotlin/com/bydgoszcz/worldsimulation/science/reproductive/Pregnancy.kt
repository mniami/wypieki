package com.bydgoszcz.worldsimulation.science.reproductive

import com.bydgoszcz.worldsimulation.actions.ReproductiveManAction
import com.bydgoszcz.worldsimulation.extensions.getCurrentCouple
import com.bydgoszcz.worldsimulation.helpers.RandomHelper
import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.history.PregnantHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.utils.findFirstOrNull
import com.bydgoszcz.worldsimulation.worlds.World

class Pregnancy {
    val minAgeToBePregnant = 18

    fun isTimeToBePregnant(person: Person, world: World): Boolean {
        val currentCoupleEvent = person.getCurrentCouple(world) ?: return false

        return areAbleToHaveKid(currentCoupleEvent.person1, world) &&
                areAbleToHaveKid(currentCoupleEvent.person2, world)
    }

    fun bePregnant(it: Person, currentCoupleEvent: CoupleHistoryEvent, world: World) {
        val coupleTime = world.time - currentCoupleEvent.startTime
        val couple = if (currentCoupleEvent.person1 == it) currentCoupleEvent.person2 else currentCoupleEvent.person1
        val mom = if (it.sex == SexType.FEMALE) it else couple
        val dad = if (it.sex == SexType.MALE) it else couple

        if (coupleTime.years() > 2) {
            val kid = ReproductiveManAction(dad, mom).createNewPerson(world.time, world.science.dnaHelper)
            val event = PregnantHistoryEvent(mom, dad, kid, world.time, null)

            mom.history.add(event)
            dad.history.add(event)
        }
    }

    fun findPregnantHistoryEvent(person: Person): PregnantHistoryEvent? {
        return person.history.events.findFirstOrNull()
    }

    fun isTimeForDelivery(pregnantEvent: PregnantHistoryEvent?, randomHelper: RandomHelper, currentTime: WorldTime): Boolean {
        if (pregnantEvent?.bearthTime != null) {
            val bs = currentTime - pregnantEvent.begettingTime
            val typicalPregnancyPeriod = 9 * 30
            val fortune = randomHelper.getNext(3)
            val diff = Math.abs(typicalPregnancyPeriod - bs.days())

            if (diff < -10) {
                return true
            } else if (diff < 0) {
                return fortune == 1
            } else if (diff < 20) {
                return fortune == 2
            } else {
                return true
            }
        }
        return false
    }

    fun giveBirthToChild(pregnantEvent: PregnantHistoryEvent, world: World) {
        pregnantEvent.begettingTime = world.time
        world.peoples.add(pregnantEvent.kid)
    }

    fun areAbleToHaveKid(person: Person, world: World): Boolean = person.getAge(world.time).years() >= minAgeToBePregnant
}