package com.example.hw1.zoo

class ScottishFold(override val weight: Double, override val age: Int) : Cat {
    override val behaviorType = BehaviorType.PASSIVE

    override fun getAnimalType(): String {
        return "Scottish Fold"
    }
}