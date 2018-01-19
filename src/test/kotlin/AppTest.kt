import com.bydgoszcz.worldsimulation.simulations.SimulationState
import org.testng.Assert.*
import org.testng.annotations.Test
import pl.bydgoszcz.wypieki.App

class AppTest {
    val victim = App(emptyArray())

    @Test
    fun testStart() {
        victim.start()

        Thread.sleep(200)
        assertEquals(victim.simulation.getCurrentState(), SimulationState.STARTED)
    }
}