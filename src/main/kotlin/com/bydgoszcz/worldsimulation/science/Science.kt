package com.bydgoszcz.worldsimulation.science

import com.bydgoszcz.worldsimulation.science.relationships.Relationship
import com.bydgoszcz.worldsimulation.science.reproductive.Pregnancy

class Science(val dnaHelper: DnaHelper = DnaHelper(),
              val pregnancy: Pregnancy = Pregnancy(),
              val relationship: Relationship = Relationship())