package com.bydgoszcz.worldsimulation.items

class Person(var name : String = "",
             var age : Long = 0,
             var sex : SexType = SexType.MALE,
             var dna : ByteArray = ByteArray(DNA_LENGTH),
             val mom : Person?,
             val dad : Person?){
    companion object {
        val DNA_LENGTH = 100
        val SEX_CHROMOSOME_INDEX = 44
    }
}