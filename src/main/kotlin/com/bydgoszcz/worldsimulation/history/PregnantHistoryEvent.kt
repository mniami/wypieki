package com.bydgoszcz.worldsimulation.history

import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.WorldTime

data class PregnantHistoryEvent(val mom: Person, val dad: Person, val kid : Person, var begettingTime: WorldTime, var bearthTime: WorldTime? = null) : HistoryEvent("Pregnant")