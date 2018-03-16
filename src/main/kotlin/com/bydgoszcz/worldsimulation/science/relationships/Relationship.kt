package com.bydgoszcz.worldsimulation.science.relationships

import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.worlds.World

class Relationship {
    val minCoupleAge = 10

    fun lookForCouple(person: Person, world: World) : Boolean{
        if (person.getAge(world.time).years() < minCoupleAge){
            return false
        }
        val listCandidates = world.peoples.filter { possibleCouple ->
            return@filter person != possibleCouple &&
                    person.sex != possibleCouple.sex &&
                    possibleCouple.getAge(world.time).years() >= minCoupleAge
        }
        if (listCandidates.isEmpty()){
            return false
        }
        val candidateIdx = world.random.getNext(listCandidates.size)
        val candidatePerson = listCandidates[candidateIdx]
        val newCoupleEvent = CoupleHistoryEvent(person, candidatePerson, world.time, null)

        candidatePerson.history.add(newCoupleEvent)
        person.history.add(newCoupleEvent)

        return true
    }
    fun getCurrentCouple(person: Person) : CoupleHistoryEvent? {
        return person.history.events.firstOrNull{
            it is CoupleHistoryEvent && it.endTime == null
        } as CoupleHistoryEvent?
    }
}