package com.bydgoszcz.worldsimulation.science

class PersonMath {
    fun calcWill(itemList : List<WillCalcItem>) : Double {
        var sum = 0.0
        var weightSum = 0.0

        for (item in itemList){
            sum += item.value * item.weight
            weightSum += item.weight
        }
        return sum / weightSum
    }

    fun isWill(will: Double): Boolean {
        return will > 0.7
    }
}

