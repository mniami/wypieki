package com.bydgoszcz.worldsimulation.worlds

import com.bydgoszcz.worldsimulation.worldItems.MarketItem
import com.bydgoszcz.worldsimulation.worldItems.Person
import com.bydgoszcz.worldsimulation.worldStructures.Market
import java.util.*

class MarketWorld(val peoples: MutableList<Person> = LinkedList(),
                  val items: MutableList<MarketItem> = LinkedList(),
                  val markets: MutableList<Market> = LinkedList(),
                  var time : Long = 0,
                  var lastRandom : Int = 0)


