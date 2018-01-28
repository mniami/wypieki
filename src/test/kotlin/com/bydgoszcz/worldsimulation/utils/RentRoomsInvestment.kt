package com.bydgoszcz.worldsimulation.utils

import java.text.NumberFormat

/**
 * Rent rooms investment
 * gain = zysk,
 * tfi = Towarzystwo Funduszy Inwestycyjnych,
 * yearContribution = coroczne wplaty na konto (oszczedzanie)
 * flats = mieszkania
 * tfiGainPerc = procent zysku rocznie z oszczednosci w TFI
 */
class RentRoomsInvestment(val monthContribution: Double = 1000.0,
                          val tfiGainPerc: Double = 4.0,
                          val flatCost: Double = 220000.0,
                          val rentYears: Int = 26,
                          val flatRentGain: Double = 8000.0,
                          val pensionYears: Int = 30) {

    val formatter = NumberFormat.getCurrencyInstance()

    fun calculate(investmentStatusListener: (RentRoomInvestmentResult) -> Unit): RentRoomInvestmentResult {

        // calculations
        var cFlatRentGainTotal = 0.0
        var cTotalCapital = 0.0
        var cCapital = 0.0
        var cFlats = 0
        var cTfiGain = 0.0
        var cYearContribution = monthContribution * 12
        var cTfiGainTotal = 0.0
        var cTotalContribution = 0.0
        var cFlatRentGainForYear = 0.0
        var monthPensionFromFlatsRenting = 0.0
        var calcPension : () -> Double = {
            cFlats * (flatRentGain / 12) + cCapital / 12 / pensionYears
        }

        println(String.format("YEAR\tFlat gain\tContribution\tTFI Gain\tCapital\t\tFlats\t"))

        for (i in 1..rentYears) {
            cFlatRentGainForYear = flatRentGain * cFlats
            cFlatRentGainTotal += cFlatRentGainForYear
            cTfiGain = cCapital * (tfiGainPerc / 100.0)
            cTfiGainTotal += cTfiGain
            cCapital += cFlatRentGainForYear + cYearContribution + cTfiGain
            cTotalContribution += cYearContribution

            if (cCapital >= flatCost) {
                cCapital -= flatCost
                cFlats++
                //println("New flat bought")
            }

            cTotalCapital = flatCost * cFlats + cCapital
            monthPensionFromFlatsRenting = calcPension()

            println(String.format("%s\t%s\t%s\t%s\t\t%s\t\t%s",
                    i,
                    formatter.format(cFlatRentGainForYear),
                    formatter.format(cYearContribution),
                    formatter.format(cTfiGain),
                    formatter.format(cCapital),
                    cFlats))

            investmentStatusListener(RentRoomInvestmentResult(i, cFlatRentGainTotal, cCapital, cTfiGainTotal, cTotalCapital, cTotalContribution, cFlats, cFlatRentGainForYear, monthPensionFromFlatsRenting))

        }
        println(String.format("\nGain total: %s\nTotal capital + flats: %s\nFlats: %s\nTFI Gain: %s\nTotal contribution: %s\nCapital: %s\nFlats rent month pension: %s",
                formatter.format(cFlatRentGainTotal),
                formatter.format(cTotalCapital),
                cFlats,
                formatter.format(cTfiGainTotal),
                formatter.format(cTotalContribution),
                formatter.format(cCapital),
                formatter.format(monthPensionFromFlatsRenting)))
        return RentRoomInvestmentResult(rentYears + 1, cFlatRentGainTotal, cCapital, cTfiGainTotal, cTotalCapital, cTotalContribution, cFlats, cFlatRentGainForYear, monthPensionFromFlatsRenting)
    }
}

class RentRoomInvestmentResult(val year: Int,
                               val flatRentGainTotal: Double,
                               val capital: Double,
                               val tfiGainTotal: Double,
                               val totalCapital: Double,
                               val totalContribution: Double,
                               val flats: Int,
                               val flatGainForYear : Double,
                               val monthPensionFromFlatsRenting : Double)