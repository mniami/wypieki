package com.bydgoszcz.worldsimulation.utils

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
fun ByteArray.xor(array: ByteArray) : ByteArray{
    val result = ByteArray(this.size)
    for (i in this.indices){
        result[i] = this[i] xor array[i]
    }
    return result
}
fun String.xor(value : String) : String {
    return String(this.toByteArray(Charsets.UTF_8).xor(value.toByteArray(Charsets.UTF_8)))
}