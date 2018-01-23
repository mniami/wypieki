package com.bydgoszcz.worldsimulation.actions

import com.bydgoszcz.worldsimulation.history.HistoryAction
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.utils.xor
import com.bydgoszcz.worldsimulation.worlds.World

class ReproductiveManAction(val world : World, val dad : Person, val mom : Person) : HistoryAction() {
    val boy : Person

    init {
        val newDna = dad.dna.xor(mom.dna)
        val name = dad.name.xor(mom.name)
        val isMale = newDna[Person.SEX_CHROMOSOME_INDEX] == 1.toByte()
        val sex = if (isMale) SexType.MALE else SexType.FEMALE

        boy = Person(name, dna = newDna, sex = sex, mom = mom, dad = dad, bearthDay = world.time)
        world.peoples.add(boy)
    }
}
