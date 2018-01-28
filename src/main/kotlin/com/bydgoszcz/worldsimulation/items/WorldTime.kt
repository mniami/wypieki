package com.bydgoszcz.worldsimulation.items

data class WorldTime(private var value: Long) {
    private var year : Long = 0
    init {
        year = value / 365
    }
    fun elapsed() {
        value+=77
        year = value / 365
    }
    fun days() : Long = value
    fun dayOfYear() : Int = value.toInt() % 365
    fun monthOfYear() : Int = value.toInt() % 12
    fun year() : Long = year

    operator fun minus(otherWorldTime: WorldTime) : WorldTimeSpan
            = WorldTimeSpan(value - otherWorldTime.value)
    fun value() : Long = value
}
class WorldTimeSpan (private var value : Long = 0){
    fun days() = value
    fun months() = value / 12
    fun years() = value / 365
}