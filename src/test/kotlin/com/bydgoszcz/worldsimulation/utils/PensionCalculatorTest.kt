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
        //params
        val monthContribution = 3000.0
        val tfiGainPerc = 1.04
        val flatCost = 220000.0
        val rentYears = 26
        val incomeForFlatOnYear = 8000.0
        val pensionYears = 30

        // calculations
        var gainForYear = incomeForFlatOnYear
        var gainTotal = 0.0
        var totalCapital = flatCost
        var capital = 0.0
        var flats = 0
        var tfiGain = 0.0
        var yearContribution = monthContribution * 12
        var tfiGainTotal = 0.0
        var totalContribution = 0.0

        println(String.format("YEAR\tGain total\tTotal capital\t\tFlats\t\tTFI Gain"))

        for (i in 0..rentYears) {
            gainTotal += gainForYear
            capital += gainForYear + yearContribution
            tfiGain = capital * (tfiGainPerc - 1.0)
            tfiGainTotal += tfiGain
            capital += tfiGain
            totalCapital = gainTotal + flatCost * flats + capital
            totalContribution += yearContribution

            println (String.format("%s\t%s\t%s\t\t%s\t\t%s",
                    i,
                    formatter.format(gainTotal),
                    formatter.format(totalCapital),
                    flats,
                    formatter.format(tfiGain)))

            if (capital >= flatCost){
                capital -= flatCost
                gainForYear += incomeForFlatOnYear
                flats++
                //println("New flat bought")
            }
        }
        val monthPensionFromFlatsRenting = flats * (incomeForFlatOnYear / 12)
        println(String.format("\nGain total: %s\nTotal capital + flats: %s\nFlats: %s\nTFI Gain: %s\nTotal contribution: %s\nCapital: %s\nFlats rent month pension: %s",
                formatter.format(gainTotal),
                formatter.format(totalCapital),
                flats,
                formatter.format(tfiGainTotal),
                formatter.format(totalContribution),
                formatter.format(capital),
                formatter.format(monthPensionFromFlatsRenting)))
    }

    @Test
    fun portfelInwestycyjny(){

    }
}