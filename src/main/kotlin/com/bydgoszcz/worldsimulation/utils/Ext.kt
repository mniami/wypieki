package com.bydgoszcz.worldsimulation.utils

import java.util.*
import kotlin.experimental.xor

fun <T> T.withIn(vararg enumItems: T): Boolean {
    var found = false
    enumItems.iterator().forEachRemaining {
        if (it == this@withIn) {
            found = true
            return@forEachRemaining
        }
    }
    return found
}

fun <E, T:MutableCollection<E>> T.copy() : MutableCollection<E> = this.map {it -> it}.toMutableList()