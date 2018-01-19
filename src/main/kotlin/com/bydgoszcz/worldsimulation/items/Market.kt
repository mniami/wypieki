package com.bydgoszcz.worldsimulation.items

import java.util.*

class Market(val name : String = "", val items: MutableList<MarketItem> = LinkedList()) {
    fun addItem(item: MarketItem) {}
    fun removeItem(item: MarketItem) {}
}