package pl.bydgoszcz.wypieki

import com.bydgoszcz.worldsimulation.consoles.CommandLineConsole
import com.bydgoszcz.worldsimulation.interceptors.LogInterceptor
import com.bydgoszcz.worldsimulation.phases.AnalysePhase
import com.bydgoszcz.worldsimulation.phases.TimePhase
import com.bydgoszcz.worldsimulation.simulations.DefaultSimulation
import java.util.*

class App(val args: Array<String>) {
    val logInterceptor = LogInterceptor()
    val simulation = DefaultSimulation(
            interceptors = Arrays.asList(logInterceptor),
            phases = Arrays.asList(AnalysePhase(), TimePhase()))
    val console = CommandLineConsole(
            simulation = simulation,
            logInterceptor = logInterceptor)

    fun start() {
        Thread{ simulation.start() }.start()
        Thread{ console.start() }.start()
    }
}