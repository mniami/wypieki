package com.bydgoszcz.worldsimulation.science.relationships

import com.bydgoszcz.worldsimulation.helpers.RandomHelper
import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.history.HistoryEvent
import com.bydgoszcz.worldsimulation.history.PersonHistory
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.items.WorldTimeSpan
import com.bydgoszcz.worldsimulation.worlds.World
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.testng.Assert.assertFalse
import org.testng.Assert.assertTrue
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import java.util.*

class RelationshipTest {
    val victim = Relationship()

    @Mock
    private var person: Person = Person()
    @Mock
    private var person2: Person = Person()
    @Mock
    private var world: World = World()
    @Mock
    private var history: PersonHistory = PersonHistory()
    @Mock
    private var random: RandomHelper = RandomHelper()

    @BeforeTest
    fun setUp() {
        initMocks(this)

        whenever(person.sex).thenReturn(SexType.MALE)
        whenever(person2.sex).thenReturn(SexType.FEMALE)
        whenever(person2.getAge(any())).thenReturn(WorldTimeSpan.fromYears(victim.minCoupleAge + 1))

        whenever(world.peoples).thenReturn(mutableListOf(person, person2))
        whenever(world.time).thenReturn(WorldTime(2))
        whenever(world.random).thenReturn(random)

        whenever(person.history).thenReturn(history)
        whenever(person2.history).thenReturn(history)
        whenever(person.getAge(any())).thenReturn(WorldTimeSpan.fromYears(2))
    }

    @Test
    fun testLookForCouple_notAvailableForCoupling() {
        for (personAgeInYears in 0..(victim.minCoupleAge - 1)) {
            whenever(person.getAge(any())).thenReturn(WorldTimeSpan.fromYears(personAgeInYears))

            assertFalse(victim.lookForCouple(person, world))
        }
    }

    @Test
    fun testLookForCouple_notAvailable_alreadyCoupled() {
        whenever(history.events).thenReturn(LinkedList<HistoryEvent>(listOf(CoupleHistoryEvent(person, person2, WorldTime(2), null))))

        for (personAgeInYears in 0..(victim.minCoupleAge - 1)) {
            whenever(person.getAge(any())).thenReturn(WorldTimeSpan.fromYears(personAgeInYears))

            assertFalse(victim.lookForCouple(person, world))
        }
    }


    @Test
    fun testGetCurrentCouple_success() {
        whenever(person.getAge(any())).thenReturn(WorldTimeSpan.fromYears(20))
        whenever(person2.getAge(any())).thenReturn(WorldTimeSpan.fromYears(20))
        whenever(history.events).thenReturn(LinkedList<HistoryEvent>(listOf(CoupleHistoryEvent(person, person2, WorldTime(2), WorldTime(2)))))

        assertTrue(victim.lookForCouple(person, world))
        verify(history, times(2)).add(any())
    }
}
