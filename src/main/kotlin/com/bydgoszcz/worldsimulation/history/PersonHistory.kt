package com.bydgoszcz.worldsimulation.history

import java.util.*

class PersonHistory(private val historyEvents: MutableList<HistoryEvent> = LinkedList()) {
    fun add(historyEvent: HistoryEvent) {
        historyEvents.add(historyEvent)
    }

    fun filter(filtr: (HistoryEvent) -> Boolean): List<HistoryEvent> = historyEvents.filter(filtr)

    fun singleOrNull(predicate: (HistoryEvent) -> Boolean): HistoryEvent? = historyEvents.singleOrNull(predicate)
}

