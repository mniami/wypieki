package com.bydgoszcz.worldsimulation.utils

import org.testng.annotations.Test
import java.math.BigDecimal
import java.math.MathContext
import java.text.NumberFormat



class PensionCalculatorTest {
    @Test
    fun testCalculator() {
        val calculator = PensionCalculator()
        val result = calculator.calculate(BigDecimal(400), InvestmentTime().applyFromYears(26), BigDecimal(2))
        val mathContext = MathContext(4)
        val pensionOnMonth = result.totalCapital.divide(BigDecimal(20 * 12), mathContext)
        val formatter = NumberFormat.getCurrencyInstance()

        println(String.format("Input: \t\t\t\t%s\n" +
                "Investment gain: \t%s\n" +
                "Total capital: \t\t%s\n" +
                "Pension on month: \t%s",
                formatter.format(result.payedCapital),
                formatter.format(result.investmentGain),
                formatter.format(result.totalCapital),
                formatter.format(pensionOnMonth)))
    }
}