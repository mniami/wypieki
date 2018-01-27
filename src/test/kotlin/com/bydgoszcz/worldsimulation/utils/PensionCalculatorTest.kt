package com.bydgoszcz.worldsimulation.utils

import org.testng.Assert.assertEquals
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
    fun rentFlat() {
        RentRoomsInvestment(500.0, 1.4, 100000.0, 35, 12000.0, 30).calculate { }
    }

    @Test
    fun testRentingFlatCalculation() {
        val investment = RentRoomsInvestment(1000.0, 1.0, 100000.0, 20, 10000.0, 30)
        val result = investment.calculate {
            when (it.year) {
                in 1..8 -> {
                    assertEquals(it.capital, it.year * 12000.0, "capital")
                    assertEquals(it.tfiGainTotal, 0.0, "tfiGainTotal")
                    assertEquals(it.flats, 0, "flats")
                }
                9 -> {
                    assertEquals(it.capital, 8000.0, "capital")
                    assertEquals(it.tfiGainTotal, 0.0, "tfiGainTotal")
                    assertEquals(it.flats, 1, "flats")
                    assertEquals(it.flatGainForYear, 0.0, "flatRentGainForYear")
                }
                14 -> {
                    assertEquals(it.capital, 18000.0, "capital")
                    assertEquals(it.tfiGainTotal, 0.0, "tfiGainTotal")
                    assertEquals(it.flats, 2, "flats")
                    assertEquals(it.flatGainForYear, 10000.0, "flatRentGainForYear")
                }
                20 -> {
                    assertEquals(it.capital, 40000.0, "capital")
                    assertEquals(it.tfiGainTotal, 0.0, "tfiGainTotal")
                    assertEquals(it.flats, 4, "flats")
                    assertEquals(it.flatGainForYear, 30000.0, "flatRentGainForYear")
                    assertEquals(it.monthPensionFromFlatsRenting, 4.0 * 10000.0 / 12.0 + 40000.0 / 12.0 / 30.0, "")
                }
            }
        }
    }

    @Test
    fun testRentingFlatCalculationWithTfi() {
        val investment = RentRoomsInvestment(1000.0, 2.0, 100000.0, 20, 10000.0, 30)
        val result = investment.calculate {
            when (it.year) {
                1 -> {
                    assertEquals(it.capital, 12000.0, "capital")
                    assertEquals(it.tfiGainTotal, 0.0, "tfiGainTotal")
                    assertEquals(it.flats, 0, "flats")
                }
                2 -> {
                    assertEquals(it.capital, 24240.0, "capital")
                    assertEquals(it.tfiGainTotal, 240.0, "tfiGainTotal")
                    assertEquals(it.flats, 0, "flats")
                }
                3 -> {
                    assertEquals(it.capital, 36724.8, "capital")
                    assertEquals(it.tfiGainTotal, 724.8, "tfiGainTotal")
                    assertEquals(it.flats, 0, "flats")
                }
            }
        }
    }
                @Test
    fun portfelInwestycyjny() {

    }
}

