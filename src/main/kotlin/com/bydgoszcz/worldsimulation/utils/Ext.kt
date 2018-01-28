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