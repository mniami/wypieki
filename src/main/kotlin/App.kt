package pl.bydgoszcz.wypieki

import com.bydgoszcz.worldsimulation.consoles.CommandLineConsole
import com.bydgoszcz.worldsimulation.interceptors.ActionExecutionInterceptor
import com.bydgoszcz.worldsimulation.phases.AnalysePhase
import com.bydgoszcz.worldsimulation.phases.GodPhase
import com.bydgoszcz.worldsimulation.phases.ReproductivePhase
import com.bydgoszcz.worldsimulation.phases.TimePhase
import com.bydgoszcz.worldsimulation.simulations.Simulation
import java.util.*

class App(val args: Array<String>) {
    val logInterceptor = ActionExecutionInterceptor()
    val simulation = Simulation(
            interceptors = Arrays.asList(logInterceptor),
            phases = Arrays.asList(AnalysePhase(), GodPhase(), ReproductivePhase(), TimePhase()))
    val console = CommandLineConsole(
            simulation = simulation,
            logInterceptor = logInterceptor)

    fun start() {
        Thread{ simulation.start() }.start()
        Thread{ console.start() }.start()
    }
}