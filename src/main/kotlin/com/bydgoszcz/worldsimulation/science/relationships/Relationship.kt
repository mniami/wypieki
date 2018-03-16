package com.bydgoszcz.worldsimulation.science.relationships

import com.bydgoszcz.worldsimulation.extensions.getCurrentCouple
import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.worlds.World

class Relationship {
    private val minimumCouplAge = 10

    fun lookForCouple(it: Person, world: World) : Boolean{
        if (it.getAge(world.time).years() < minimumCouplAge){
            return false
        }
        val listCandidates = world.peoples.filter { possibleCouple ->
            if (it != possibleCouple &&
                    it.sex != possibleCouple.sex &&
                    possibleCouple.getAge(world.time).years() >= minimumCouplAge) {
                return@filter true
            }
            return@filter false
        }
        if (listCandidates.isEmpty()){
            return false
        }
        val candidateIdx = world.random.getNext(listCandidates.size)
        val candidatePerson = listCandidates[candidateIdx]
        val newCoupleEvent = CoupleHistoryEvent(it, candidatePerson, world.time, null)

        candidatePerson.history.add(newCoupleEvent)
        it.history.add(newCoupleEvent)

        return true
    }
    fun getCurrentCouple(person: Person) : CoupleHistoryEvent? {
        return person.history.filter {
            it is CoupleHistoryEvent && it.endTime == null
        }.firstOrNull() as CoupleHistoryEvent?
    }
}