package com.bydgoszcz.worldsimulation.extensions

import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.worlds.World

fun Person.getCouple() : CoupleHistoryEvent? {
    return this.history.filter {
        if (it is CoupleHistoryEvent && it.endTime == null) {
            return@filter true
        }
        return@filter false
    }.firstOrNull() as CoupleHistoryEvent?
}

fun Person.lookForCouple(it: Person, world: World) : Boolean {
    val listCandidates = world.peoples.filter { possibleCouple ->
        if (it != possibleCouple && it.sex != possibleCouple.sex &&
                possibleCouple.getAge(world.time).years() > 18){
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