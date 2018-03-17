package com.bydgoszcz.worldsimulation.consoles

import com.bydgoszcz.worldsimulation.interceptors.ActionExecutionInterceptor
import com.bydgoszcz.worldsimulation.simulations.Simulation
import com.bydgoszcz.worldsimulation.utils.Log

class CommandLineConsole(private val log: Log = Log(),
                         private val simulation: Simulation,
                         private val logInterceptor: ActionExecutionInterceptor) {
    fun start() {
        log.d("Wypieki v.1.0")
        while (true) {
            val line = readLine() ?: ""
            val prepared = line.trim().toLowerCase()
            try {
                when (prepared) {
                    "show" -> {
                        logInterceptor.showInfo()
                    }
                    "pause" -> {
                        simulation.pause()
                    }
                    "resume" -> {
                        simulation.resume()
                    }
                    "repro" -> {
                        simulation.getActionExecutor().addNewMan(0, 1)
                    }
                    "help" -> log.d("show, pause, resume, repro, help, quit, show me [person idx], set time [time in years]")
                    "exit" -> System.exit(0)
                }
                if (prepared.startsWith("set time")){
                    val regex = Regex("set time (\\d+)")
                    val newTimeYears = Integer.parseInt(regex.matchEntire(prepared)?.groups?.get(1)?.value)
                    if (newTimeYears != null) {
                        simulation.setTime(newTimeYears * 365L)
                    }
                }
                if (prepared.startsWith("show me")) {
                    val regex = Regex("show me (\\d)")
                    val manId = Integer.parseInt(regex.matchEntire(prepared)?.groups?.get(1)?.value)
                    if (manId != null) {
                        logInterceptor.showPerson(manId)
                    }
                }
            } catch (ex: Exception) {
                log.d(ex.toString())
            }
        }
    }
}