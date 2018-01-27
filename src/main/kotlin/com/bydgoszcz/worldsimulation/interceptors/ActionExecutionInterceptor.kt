package com.bydgoszcz.worldsimulation.interceptors

import com.bydgoszcz.worldsimulation.utils.Log
import com.bydgoszcz.worldsimulation.worlds.World
import java.util.*

class ActionExecutionInterceptor(val l: Log = Log()) : Interceptor() {
    private val actions = PriorityQueue<String>()
    var cachedWorld : World? = null

    override fun execute(world: World) {
        cachedWorld = world
        actions.forEach {
            when (it) {
                "ShowInfo" -> {
                    l.d(String.format("People: %s\nMarket: %s\nItems: %s\nTime: %s years %s days\nRandom: %s\n",
                            world.peoples.size,
                            world.markets.size,
                            world.items.size,
                            world.time.year(),
                            world.time.days(),
                            world.lastRandom))
                }
            }
            actions.remove(it)
        }
    }

    fun showInfo() {
        actions.add("ShowInfo")
    }

    fun showPerson(personId : Int){
        if (cachedWorld != null){
            val person = cachedWorld?.peoples?.get(personId)
            if (person != null) {
                l.d(person)
            }
        }
    }
}