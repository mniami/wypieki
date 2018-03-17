package com.bydgoszcz.worldsimulation.history

import java.util.*

data class PersonHistory(private val historyEvents: MutableList<HistoryEvent> = LinkedList()) {
    val events : List<HistoryEvent>
        get() = historyEvents

    fun add(historyEvent: HistoryEvent) {
        historyEvents.add(historyEvent)
    }
}

