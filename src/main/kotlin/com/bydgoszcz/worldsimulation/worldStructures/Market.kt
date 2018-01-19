package com.bydgoszcz.worldsimulation.worldStructures

import com.bydgoszcz.worldsimulation.worldItems.MarketItem
import java.util.*

class Market(val name : String = "", val items: MutableList<MarketItem> = LinkedList()) {
    fun addItem(item: MarketItem) {}
    fun removeItem(item: MarketItem) {}
}