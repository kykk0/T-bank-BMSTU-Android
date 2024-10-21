package com.example.hw1.zoo

class Corgi(override val weight: Double, override val age: Int) : Dog {
    override val biteType = BiteType.OVERBITE

    override fun getAnimalType(): String {
        return "Corgi"
    }
}