package com.bydgoszcz.worldsimulation.science

import com.bydgoszcz.worldsimulation.items.Person
import java.util.*
import kotlin.experimental.xor

class DnaHelper {
    enum class DnaIndex {
        WillingToHaveKids
    }

    fun dnaValue(person: Person, dnaIndex: DnaIndex) : Double {
        return person.dna[dnaIndex.ordinal] / 255.0
    }

    fun dnaXor(array1 : ByteArray, array2: ByteArray) : ByteArray{
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