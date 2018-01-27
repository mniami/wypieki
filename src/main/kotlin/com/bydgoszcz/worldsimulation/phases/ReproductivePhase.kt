package com.bydgoszcz.worldsimulation.phases

import com.bydgoszcz.worldsimulation.actions.ReproductiveManAction
import com.bydgoszcz.worldsimulation.extensions.getCouple
import com.bydgoszcz.worldsimulation.helpers.PregnancyHelper
import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.history.PregnantHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.worlds.World
import java.util.*

class ReproductivePhase : Phase("Reproductive phase") {
    override fun execute(world: World) {
        val pregnancyHelper = PregnancyHelper()

        LinkedList(world.peoples).forEach {
            val pregnantEvent = pregnancyHelper.getCurrentPregnant(it)
            if (pregnantEvent != null && pregnantEvent.bearthTime == null &&
                    pregnancyHelper.isTimeForDelivery(pregnantEvent, world.random, world.time)) {
                //TODO move it to some action mechanism, as this should be noticed and log somewhere
                //TODO it is really important that new person is added to the world

                pregnancyHelper.giveBirthToChild(pregnantEvent, world)
            }
            else {
                val currentCoupleEvent = it.getCouple()

                val age = it.getAge(world.time).years()
                if (currentCoupleEvent is CoupleHistoryEvent && currentCoupleEvent.endTime == null) {
                    bePregnant(it, currentCoupleEvent, world)
                } else if (age > 18) {
                    lookForCouple(it, world)
                }
            }
        }
    }

    private fun lookForCouple(it: Person, world: World) {
        val listCandidates = world.peoples.filter { possibleCouple ->
            if (it != possibleCouple && it.sex != possibleCouple.sex &&
                    possibleCouple.getAge(world.time).years() > 18){
                return@filter true
            }
            return@filter false
        }
        val candidateIdx = world.random.getNext(listCandidates.size)
        val candidatePerson = listCandidates[candidateIdx]
        val newCoupleEvent = CoupleHistoryEvent(it, candidatePerson, world.time, null)

        candidatePerson.history.add(newCoupleEvent)
        it.history.add(newCoupleEvent)
    }

    private fun bePregnant(it: Person, currentCoupleEvent: CoupleHistoryEvent, world: World) {
        val coupleTime = world.time - currentCoupleEvent.startTime
        val couple = if (currentCoupleEvent.person1 == it) currentCoupleEvent.person2 else currentCoupleEvent.person1
        val mom = if (it.sex == SexType.FEMALE) it else couple
        val dad = if (it.sex == SexType.MALE) it else couple

        if (coupleTime.years() > 2){
            val kid = ReproductiveManAction(dad, mom).createNewPerson(world.time)
            val event = PregnantHistoryEvent(mom, dad, kid, world.time, null)

            mom.history.add(event)
            dad.history.add(event)
        }
    }
}