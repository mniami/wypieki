package pl.bydgoszcz.wypieki

import com.bydgoszcz.worldsimulation.consoles.CommandLineConsole
import com.bydgoszcz.worldsimulation.interceptors.LogInterceptor
import com.bydgoszcz.worldsimulation.phases.AnalysePhase
import com.bydgoszcz.worldsimulation.phases.TimePhase
import com.bydgoszcz.worldsimulation.simulations.Simulation
import java.util.*

class App(val args: Array<String>) {
    fun start() {
        val logInterceptor = LogInterceptor()
        val simulation = Simulation(
                interceptors = Arrays.asList(logInterceptor),
                phases = Arrays.asList(AnalysePhase(), TimePhase()))
        val console = CommandLineConsole(
                simulation = simulation,
                logInterceptor = logInterceptor)

        Thread{ simulation.start() }.start()
        console.start()
    }
}