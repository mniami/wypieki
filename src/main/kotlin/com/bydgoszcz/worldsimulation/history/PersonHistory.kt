package com.bydgoszcz.worldsimulation.history

import java.util.*

class PersonHistory(private val historyEvents: MutableList<HistoryEvent> = LinkedList()) {
    val events : List<HistoryEvent>
        get() = historyEvents

    fun add(historyEvent: HistoryEvent) {
        historyEvents.add(historyEvent)
    }
}

