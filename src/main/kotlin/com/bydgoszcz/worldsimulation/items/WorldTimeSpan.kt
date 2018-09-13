package com.bydgoszcz.worldsimulation.items

class WorldTimeSpan (private var value : Long = 0){
    fun days() = value
    fun months() = value / 12
    fun years() = value / 365

    companion object {
        fun fromYears(years : Int) : WorldTimeSpan {
            return WorldTimeSpan(years * 365L)
        }
    }
}