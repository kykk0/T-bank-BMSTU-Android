package com.example.hw1.zoo

class Husky(override val weight: Double, override val age: Int) : Dog {
    override val biteType= BiteType.STRAIGHT

    override fun getAnimalType(): String {
        return "Husky"
    }
}