package com.bydgoszcz.worldsimulation.simulations

import com.bydgoszcz.worldsimulation.interceptors.Interceptor
import com.bydgoszcz.worldsimulation.phases.Phase
import com.bydgoszcz.worldsimulation.utils.Log
import com.bydgoszcz.worldsimulation.utils.withIn
import com.bydgoszcz.worldsimulation.worlds.World

class DefaultSimulation(private val world: World = World(),
                        private var state: SimulationState = SimulationState.UNKNOWN,
                        private val log : Log = Log(),
                        private val phases: List<Phase>,
                        val interceptors : List<Interceptor>) {
    fun start() {
        if (state.withIn(SimulationState.STARTED, SimulationState.PAUSED)) {
            throw Exception("Already started")
        }

        state = SimulationState.STARTED

        log.d("Simulation started")

        mainLoop()
    }

    fun pause() {
        if (state.withIn(SimulationState.UNKNOWN)){
            throw Exception("Not even started")
        }

        state = SimulationState.PAUSED
        log.d("Simulation paused")
    }

    fun resume(){
        if (state != SimulationState.PAUSED){
            throw Exception("Not proper state")
        }

        state = SimulationState.STARTED
        log.d("Simulation resumed")
    }


    fun stop() {
        if (state.withIn(SimulationState.UNKNOWN)){
            throw Exception("Not even started")
        }

        state = SimulationState.STOPPED
        log.d("Simulation stopped")
    }

    private fun mainLoop(){
        while(true){
            if (state == SimulationState.STARTED) {
                phases.forEach {
                    it.execute(world)
                }
            }
            interceptors.forEach { it.execute(world) }
            Thread.sleep(500)
        }
    }
    fun getCurrentState() : SimulationState {
        return state
    }
}