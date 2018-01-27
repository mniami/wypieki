package com.bydgoszcz.worldsimulation.actions

import com.bydgoszcz.worldsimulation.history.HistoryAction
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.worlds.World
import java.util.*
import kotlin.experimental.xor

class ReproductiveManAction(val dad : Person, val mom : Person) : HistoryAction() {
    var boy : Person? = null

    fun createNewPerson(worldTime : WorldTime) : Person {
        val newDna = dnaXor(dad.dna, mom.dna)
        val name = "Adas"
        val isMale = newDna[Person.SEX_CHROMOSOME_INDEX] == 1.toByte()
        val sex = if (isMale) SexType.MALE else SexType.FEMALE

        val newPerson = Person(name, dna = newDna, sex = sex, mom = mom, dad = dad, bearthDay = worldTime)
        boy = newPerson

        return newPerson
    }

    fun addNewPersonToWorld(world : World){
        val newPerson = createNewPerson(world.time)
        world.peoples.add(newPerson)
    }

    private fun dnaXor(array1 : ByteArray, array2: ByteArray) : ByteArray{
        val result = ByteArray(Math.max(array1.size, array2.size))
        val random = Random()

        for (i in array1.indices){
            val x = if (array1.size > i) array1[i] else array2[i]
            val y = if (array2.size > i) array2[i] else x

            val rand = random.nextInt(55)
            var choose = 0.toByte()

            when(rand){
                in 0..20 -> choose = x
                in 20..50 -> choose = y
                in 50..55 -> choose = x xor y
            }

            result[i] = choose
        }
        return result
    }
}
