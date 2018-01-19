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
fun ByteArray.xor(array: ByteArray) : ByteArray{
    val result = ByteArray(Math.max(this.size, array.size))
    val random = Random()

    for (i in this.indices){
        val x = if (this.size > i) this[i] else array[i]
        val y = if (array.size > i) array[i] else x

        val rand = random.nextInt(55)
        var choose = 0.toByte()

        when(rand){
            in 0..20 -> choose = x
            in 20..50 -> choose = y
            in 50..55 -> choose = x xor y
        }

        result[i] = choose
    }
    return result
}
fun String.xor(value : String) : String {
    return String(this.toByteArray(Charsets.UTF_8).xor(value.toByteArray(Charsets.UTF_8)))
}