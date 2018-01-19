package com.bydgoszcz.worldsimulation.utils

class Log {
    fun d(anyObject: String, vararg arguments: Any) {
        println(String.format(anyObject, arguments))
        print(">")
    }
    fun d(anyObject: Any){
        d(anyObject.toString())
    }
}