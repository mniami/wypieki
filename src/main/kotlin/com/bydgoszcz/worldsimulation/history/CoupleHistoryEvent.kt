package com.bydgoszcz.worldsimulation.history

import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.WorldTime

class CoupleHistoryEvent(val person1: Person,
                         val person2: Person,
                         val startTime: WorldTime,
                         var endTime: WorldTime? = null)
    : HistoryEvent("Couple")