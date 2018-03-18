package com.bydgoszcz.worldsimulation.science.reproductive

import com.bydgoszcz.worldsimulation.helpers.RandomHelper
import com.bydgoszcz.worldsimulation.history.CoupleHistoryEvent
import com.bydgoszcz.worldsimulation.history.PregnantHistoryEvent
import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.items.WorldTimeSpan
import com.bydgoszcz.worldsimulation.worlds.World
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.testng.Assert.*
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

class PregnancyTest {
    val victim = Pregnancy()

    @Mock
    val person: Person = Person()
    @Mock
    val person2: Person = Person()
    @Mock
    val kid : Person = Person()
    @Mock
    val world: World = World()
    @Mock
    val random: RandomHelper = RandomHelper()

    @BeforeTest
    fun setUp() {
        initMocks(this)

        whenever(world.science).thenReturn(mock())
        whenever(world.science.relationship).thenReturn(mock())
        whenever(person.history).thenReturn(mock())
        whenever(world.time).thenReturn(WorldTime(30))
        whenever(person.getAge(any())).thenReturn(WorldTimeSpan.fromYears(victim.minAgeToBePregnant))
        whenever(kid.mom).thenReturn(person)
        whenever(kid.dad).thenReturn(person2)
        whenever(random.getNext()).thenReturn(1)
        whenever(random.getNext(any())).thenReturn(1)
    }

    @Test
    fun testIsTimeToBePregnant_success() {
        givenCouple()
        assertTrue(victim.isTimeToBePregnant(person, world))
    }

    @Test
    fun testBePregnant() {
    }

    @Test
    fun testFindPregnantHistoryEvent() {
        val pregnantHistoryEvent = PregnantHistoryEvent(person, person2, kid, WorldTime(10))
        whenever(person.history.events).thenReturn(mutableListOf(pregnantHistoryEvent))
        assertEquals(victim.findPregnantHistoryEvent(person), pregnantHistoryEvent)
    }

    @Test
    fun testIsTimeForDelivery_success() {
        val pregnantHistoryEvent = PregnantHistoryEvent(person, person2, kid,
                begettingTime = WorldTime(10),
                bearthTime = null)
        whenever(person.history.events).thenReturn(mutableListOf(pregnantHistoryEvent))

        assertTrue(victim.isTimeForBirth(pregnantHistoryEvent, random, world.time))
    }

    @Test
    fun test(){
        assertEquals(2 * 2, 4, "should be 4")
        assertEquals(2 * 2, 5, "should be 5")
    }

    @Test
    fun testIsTimeForDelivery_failed() {
        val pregnantHistoryEvent = PregnantHistoryEvent(person, person2, kid, WorldTime(10))
        whenever(person.history.events).thenReturn(mutableListOf(pregnantHistoryEvent))

        assertFalse(victim.isTimeForBirth(pregnantHistoryEvent, random, world.time))
    }

    @Test
    fun testGiveBirthToChild() {
    }

    @Test
    fun testAreAbleToHaveKid() {
        givenCouple()
        assertTrue(victim.areAbleToHaveKid(person, world))
    }

    private fun givenCouple() {
        val coupleHistoryEvent = CoupleHistoryEvent(person, person2, WorldTime(30))

        whenever(person.history.events).thenReturn(mutableListOf(coupleHistoryEvent))
        whenever(person.getAge(any())).thenReturn(WorldTimeSpan.fromYears(victim.minAgeToBePregnant))
        whenever(person2.getAge(any())).thenReturn(WorldTimeSpan.fromYears(victim.minAgeToBePregnant))
        whenever(world.science.relationship.getCurrentCouple(any())).thenReturn(coupleHistoryEvent)
    }
}