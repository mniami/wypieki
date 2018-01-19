package com.bydgoszcz.worldsimulation.interceptors

import com.bydgoszcz.worldsimulation.utils.Log
import com.bydgoszcz.worldsimulation.worlds.MarketWorld
import java.util.*

class LogInterceptor(val l: Log = Log()) : Interceptor() {
    private val actions = PriorityQueue<String>()

    override fun execute(world: MarketWorld) {
        actions.forEach {
            when (it) {
                "ShowInfo" -> {
                    l.d(String.format("People: %s\nMarket: %s\nItems: %s\nTime: %s\nRandom: %s\n",
                            world.peoples.size,
                            world.markets.size,
                            world.items.size,
                            world.time,
                            world.lastRandom))
                }
            }
            actions.remove(it)
        }
    }

    fun showInfo() {
        actions.add("ShowInfo")
    }
}