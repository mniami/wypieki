package com.bydgoszcz.worldsimulation.actions

import com.bydgoszcz.worldsimulation.history.HistoryAction
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.science.DnaHelper
import com.bydgoszcz.worldsimulation.worlds.World

class ReproductiveManAction(val dad : Person, val mom : Person) : HistoryAction() {
    var boy : Person? = null

    fun createNewPerson(worldTime : WorldTime, dnaHelper: DnaHelper) : Person {
        val newDna = dnaHelper.dnaXor(dad.dna, mom.dna)
        val isMale = newDna[Person.SEX_CHROMOSOME_INDEX] == 1.toByte()
        val sex = if (isMale) SexType.MALE else SexType.FEMALE
        val name = "$sex ${worldTime.value()}"

        val newPerson = Person(name,
                dna = newDna,
                sex = sex,
                mom = mom,
                dad = dad,
                bearthDay = worldTime)
        boy = newPerson

        return newPerson
    }

    fun addNewPersonToWorld(world : World){
        val newPerson = createNewPerson(world.time, world.science.dnaHelper)
        world.peoples.add(newPerson)
    }
}
