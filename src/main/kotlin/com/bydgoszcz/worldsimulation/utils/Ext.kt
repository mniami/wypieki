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

fun <E, T:MutableCollection<E>> T.copy() : MutableCollection<E> = this.map {it -> it}.toMutableList()
inline fun <reified K:T, T> List<T>.findFirstOrNull(): K? = firstOrNull { it is K} as K?
inline fun <reified K:T, T> List<T>.findFirstOrNull(predicated: (K:T)->Boolean): K? = firstOrNull { it is K && predicated(it)} as K?
