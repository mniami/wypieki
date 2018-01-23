package com.bydgoszcz.worldsimulation.helpers

import java.util.*

class RandomHelper(private val random : Random = Random()) {
    fun getNext() = random.nextInt()
    fun getNext(maxValue : Int) = random.nextInt(maxValue)
}