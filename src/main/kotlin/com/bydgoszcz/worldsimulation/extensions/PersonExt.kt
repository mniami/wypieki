package com.bydgoszcz.worldsimulation.extensions

import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.worlds.World

fun Person.getCurrentCouple(world: World) : CoupleHistoryEvent?
    = world.science.relationship.getCurrentCouple(this)

fun Person.lookForCouple(world: World) : Boolean
    = world.science.relationship.lookForCouple(this, world)

fun Person.areAbleToHaveKid(world: World) : Boolean
        = world.science.pregnancy.areAbleToHaveKid(this, world)