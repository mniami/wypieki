package com.bydgoszcz.worldsimulation.history

import java.util.*
import kotlin.collections.*

class HistoryLog(private val actions : MutableList<HistoryAction> = LinkedList()) {
    fun add(action : HistoryAction) {
        actions.add(action)
    }
    fun filter(predicate: (HistoryAction) -> Boolean) : List<HistoryAction> =  actions.filter(predicate)
}