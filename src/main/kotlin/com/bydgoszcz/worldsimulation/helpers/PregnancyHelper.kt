package com.bydgoszcz.worldsimulation.helpers

import com.bydgoszcz.worldsimulation.history.PregnantHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.worlds.World

class PregnancyHelper {
    fun getCurrentPregnant(person: Person) : PregnantHistoryEvent? {
        return person.history.filter {
            if (it is PregnantHistoryEvent) {
                if (it.bearthTime == null){
                    return@filter true
                }
            }
            return@filter false
        }.firstOrNull() as PregnantHistoryEvent?
    }

    fun isTimeForDelivery(pregnantEvent: PregnantHistoryEvent?, randomHelper: RandomHelper, currentTime : WorldTime): Boolean {
        if (pregnantEvent != null){
            val bs = currentTime - pregnantEvent.begettingTime
            val typicalPregnancyPeriod = 9 * 30
            val fortune = randomHelper.getNext(3)
            val diff = Math.abs(typicalPregnancyPeriod - bs.days())

            if (diff < -10){
                return true
            }
            else if (diff < 0){
                return fortune == 1
            }
            else if (diff < 20){
                return fortune == 2
            }
        }
        return false
    }

    fun giveBirthToChild(pregnantEvent: PregnantHistoryEvent, world : World) {
        pregnantEvent.begettingTime = world.time
        world.peoples.add(pregnantEvent.kid)
    }
}