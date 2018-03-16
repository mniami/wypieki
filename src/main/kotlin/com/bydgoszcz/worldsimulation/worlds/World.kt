package com.bydgoszcz.worldsimulation.worlds

import com.bydgoszcz.worldsimulation.helpers.RandomHelper
import com.bydgoszcz.worldsimulation.history.HistoryLog
import com.bydgoszcz.worldsimulation.items.MarketItem
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.Market
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.science.DnaHelper
import com.bydgoszcz.worldsimulation.science.Science
import java.util.*

class World(val peoples: MutableList<Person> = LinkedList(),
            val items: MutableList<MarketItem> = LinkedList(),
            val markets: MutableList<Market> = LinkedList(),
            var time : WorldTime = WorldTime(0),
            var lastRandom : Int = 0,
            var historyLog : HistoryLog = HistoryLog(),
            var random : RandomHelper = RandomHelper(),
            val science : Science = Science(DnaHelper()))


