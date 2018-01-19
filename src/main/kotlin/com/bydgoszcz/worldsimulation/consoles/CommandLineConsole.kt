package com.bydgoszcz.worldsimulation.consoles

import com.bydgoszcz.worldsimulation.interceptors.LogInterceptor
import com.bydgoszcz.worldsimulation.simulations.DefaultSimulation
import com.bydgoszcz.worldsimulation.utils.Log

class CommandLineConsole(private val log: Log = Log(),
                         private val simulation: DefaultSimulation,
                         private val logInterceptor: LogInterceptor) {
    fun start() {
        log.d("Wypieki v.1.0")
        while (true) {
            var line = readLine() ?: ""
            when (line.trim().toLowerCase()) {
                "show" -> {
                    logInterceptor.showInfo()
                }
                "pause" -> {
                    simulation.pause()
                }
                "resume" -> {
                    simulation.resume()
                }
                "quit" -> System.exit(0)
            }
        }
    }
}