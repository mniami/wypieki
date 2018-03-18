package com.bydgoszcz.worldsimulation.science

import org.testng.Assert.assertEquals
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class PersonMathTest {
    val victim = PersonMath()

    @DataProvider
    fun calcWillDataProvider(): Array<Array<Any>> {
        return arrayOf(
                arrayOf(
                        listOf(WillCalcItem(0.5, 1.0),
                                WillCalcItem(0.5, 1.0)),
                        0.5),
                arrayOf(
                        listOf(WillCalcItem(0.5, 0.5),
                                WillCalcItem(0.5, 0.5)),
                        0.5
                ),
                arrayOf(
                        listOf(WillCalcItem(1.0, 0.5),
                                WillCalcItem(0.5, 0.5)),
                        0.75
                )
        )
    }

    @Test(dataProvider = "calcWillDataProvider")
    fun testCalcWill(list: List<WillCalcItem>, expected: Double) {
        val value = victim.calcWill(list)
        assertEquals(value, expected)
    }

    @Test
    fun testIsWill() {
    }
}