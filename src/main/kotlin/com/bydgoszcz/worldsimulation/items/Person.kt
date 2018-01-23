package com.bydgoszcz.worldsimulation.items

import com.bydgoszcz.worldsimulation.history.PersonHistory
import java.util.*

class Person(var name: String = "",
             var bearthDay: WorldTime,
             var sex: SexType = SexType.MALE,
             var dna: ByteArray = createDna(),
             var history: PersonHistory = PersonHistory(),
             val mom: Person?,
             val dad: Person?) {
    companion object {
        val DNA_LENGTH = 100
        val SEX_CHROMOSOME_INDEX = 44

        fun createDna(): ByteArray {
            val random = Random()
            val result = ByteArray(DNA_LENGTH)

            for (i in result.indices) {
                result[i] = (random.nextInt()).toByte()
            }
            return result
        }
    }
}

