package com.example.hw1.zoo

class SiameseCat(override val weight: Double, override val age: Int) : Cat {
    override val behaviorType = BehaviorType.ACTIVE

    override fun getAnimalType(): String {
        return "Siamese cat"
    }
}