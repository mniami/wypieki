package com.bydgoszcz.worldsimulation.science.reproductive

import com.bydgoszcz.worldsimulation.actions.ReproductiveManAction
import com.bydgoszcz.worldsimulation.extensions.getCurrentCouple
import com.bydgoszcz.worldsimulation.helpers.RandomHelper
import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.history.PregnantHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.science.DnaHelper
import com.bydgoszcz.worldsimulation.science.PersonMath
import com.bydgoszcz.worldsimulation.science.WillCalcItem
import com.bydgoszcz.worldsimulation.utils.findFirstOrNull
import com.bydgoszcz.worldsimulation.worlds.World

class Pregnancy {
    val minAgeToBePregnant = 18
    val pregnancyMonthAmount = 9
    val pregnancyDaysAmount = pregnancyMonthAmount * 30

    fun isTimeToBePregnant(person: Person, world: World): Boolean {
        val currentCoupleEvent = person.getCurrentCouple(world) ?: return false

        return areAbleToHaveKid(currentCoupleEvent.person1, world) &&
                areAbleToHaveKid(currentCoupleEvent.person2, world) &&
                isWillingToHaveKid(person, world)
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
        return person.history.events.findFirstOrNull { it is PregnantHistoryEvent && it.bearthTime == null}
    }

    fun isTimeForBirth(pregnantEvent: PregnantHistoryEvent, randomHelper: RandomHelper, currentTime: WorldTime): Boolean {
        if (pregnantEvent.bearthTime != null) {
            return false
        }
        val currentPregnancyPeriod = currentTime - pregnantEvent.begettingTime
        val fortune = randomHelper.getNext(3)
        val diff = pregnancyDaysAmount - currentPregnancyPeriod.days()

        // diff less than 0, it's after pregnancy period, should birth
        if (diff < -10) {
            return true
        } else if (diff < 0) {
            return fortune == 1
        } else if (diff < 20) {
            return fortune == 2
        } else {
            return true
        }
        return false
    }

    fun giveBirthToChild(pregnantEvent: PregnantHistoryEvent, world: World) {
        pregnantEvent.begettingTime = world.time
        world.peoples.add(pregnantEvent.kid)
    }

    fun isWillingToHaveKid(person: Person, world: World) : Boolean{
        if (isPregnant(person)){
            return false
        }
        val personMath = PersonMath()
        val dnaHelper = DnaHelper()

        val lastPregnancy = person.history.events.lastOrNull{ it is PregnantHistoryEvent } as PregnantHistoryEvent?
        var dnaInfluence = dnaHelper.dnaValue(person, DnaHelper.DnaIndex.WillingToHaveKids)
        val experienceInfluence = if (lastPregnancy == null) 1.0 else 0.5
        val will = personMath.calcWill(listOf(
                WillCalcItem(dnaInfluence, 0.3),
                WillCalcItem(world.globalRandoms.social, 0.3),
                WillCalcItem(experienceInfluence, 0.3)))

        return personMath.isWill(will)
    }

    fun isPregnant(person: Person): Boolean = findPregnantHistoryEvent(person) != null

    fun areAbleToHaveKid(person: Person, world: World): Boolean = person.getAge(world.time).years() >= minAgeToBePregnant
}