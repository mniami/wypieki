package com.bydgoszcz.worldsimulation.utils

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