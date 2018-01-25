package com.bydgoszcz.worldsimulation.utils

import org.testng.annotations.Test
import java.math.BigDecimal
import java.math.MathContext
import java.text.NumberFormat


class PensionCalculatorTest {
    val formatter = NumberFormat.getCurrencyInstance()
    @Test
    fun pensionCalculation() {
        val calculator = PensionCalculator()
        val result = calculator.calculate(BigDecimal(3000), InvestmentTime().applyFromYears(26), BigDecimal(4))
        val mathContext = MathContext(4)
        val yearsOfPension = 20
        val pensionOnMonth = result.totalCapital.divide(BigDecimal(yearsOfPension * 12), mathContext)

        println(String.format("Input: \t\t\t\t%s\n" +
                "Investment gain: \t%s\n" +
                "Total capital: \t\t%s\n" +
                "Pension on month: \t%s",
                formatter.format(result.payedCapital),
                formatter.format(result.investmentGain),
                formatter.format(result.totalCapital),
                formatter.format(pensionOnMonth)))
    }

    @Test
    fun rentingFlatCalculation() {
        val rentYears = 21
        val gainForFlat = 10000.0
        var gainForYear = gainForFlat
        var gainTotal = 0.0
        val flatCost = 220000.0
        var totalCapital = flatCost
        var capital = 0.0
        var flats = 1
        val tfiGainPerc = 1.04
        var tfiGain = 0.0
        var yearContribution = 3000.0 * 12
        var tfiGainTotal = 0.0
        var totalContribution = 0.0

        for (i in 0..rentYears) {
            gainTotal += gainForYear
            capital += gainForYear + yearContribution
            tfiGain = capital * (tfiGainPerc - 1.0)
            tfiGainTotal += tfiGain
            capital += tfiGain
            totalCapital = gainTotal + flatCost * flats + capital
            totalContribution += yearContribution

            println (String.format("%s\t\t%s\t%s\t\t%s\t\t%s",
                    i,
                    formatter.format(gainTotal),
                    formatter.format(totalCapital),
                    flats,
                    formatter.format(tfiGain)))

            if (capital >= flatCost){
                capital -= flatCost
                gainForYear += gainForFlat
                flats++
                println("New flat bought")
            }
        }
        println(String.format("\nGain total: %s\nTotal capital: %s\nFlats: %s\nTFI Gain: %s\nTotal contribution: %s\n",
                formatter.format(gainTotal),
                formatter.format(totalCapital),
                flats,
                formatter.format(tfiGainTotal),
                formatter.format(totalContribution)))
    }

    @Test
    fun portfelInwestycyjny(){

    }
}