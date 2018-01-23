package com.bydgoszcz.worldsimulation.actions

import com.bydgoszcz.worldsimulation.history.HistoryAction
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.worlds.World

class CreateManAction(val world: World, val person : Person) : HistoryAction() {
    init {
        world.peoples.add(person)
        world.historyLog.add(this)
    }
}