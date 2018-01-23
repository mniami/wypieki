package com.bydgoszcz.worldsimulation.items

class WorldTime(private var value: Long, private var year : Long = 0) {
    init {
        value = year / 365
    }
    fun elapsed() {
        value++
        year = value / 365
    }
    fun days() : Long = value
    fun dayOfYear() : Int = value.toInt() % 365
    fun monthOfYear() : Int = value.toInt() % 12
    fun year() : Long = year

    operator fun minus(otherWorldTime: WorldTime) : WorldTimeSpan
            = WorldTimeSpan(otherWorldTime.value - value)
}
class WorldTimeSpan (private var value : Long = 0){
    fun days() = value
    fun months() = value / 12
    fun years() = value / 365
}