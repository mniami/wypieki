package com.bydgoszcz.worldsimulation.helpers

import org.testng.annotations.Test

import org.testng.Assert.*

class RandomHelperTest {

    val victim = RandomHelper()
    @Test
    fun testNextGaussian() {
        for (i in 1..100) {
            val number = victim.nextGaussian()
            println(number)
        }
    }
}