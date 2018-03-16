package com.bydgoszcz.worldsimulation.science.relationships

import com.bydgoszcz.worldsimulation.items.Person
import com.bydgoszcz.worldsimulation.items.SexType
import com.bydgoszcz.worldsimulation.items.WorldTime
import com.bydgoszcz.worldsimulation.items.WorldTimeSpan
import com.bydgoszcz.worldsimulation.worlds.World
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.testng.Assert.assertFalse
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class RelationshipTest {
    val victim = Relationship()

    @Test
    fun testLookForCouple_notAvailableForCoupling() {
        val person: Person = mock()
        val person2: Person = mock()
        val world: World = mock()

        whenever(person.sex).thenReturn(SexType.MALE)
        whenever(person2.sex).thenReturn(SexType.FEMALE)
        whenever(person2.getAge(any())).thenReturn(WorldTimeSpan.fromYears(victim.minCoupleAge + 1))

        whenever(world.peoples).thenReturn(mutableListOf(person, person2))
        whenever(world.time).thenReturn(WorldTime(0))

        for (personAgeInYears in 0..(victim.minCoupleAge - 1)) {
            whenever(person.getAge(any())).thenReturn(WorldTimeSpan.fromYears(personAgeInYears))

            assertFalse(victim.lookForCouple(person, world))
        }
    }

    @Test
    fun testGetCurrentCouple() {
    }
}