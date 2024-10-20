package com.example.hw1.zoo

class ZooStore {
    fun identifyAnimal(animal: Animal): String{
        return when(animal){
            is Dog -> "This is a dog of type: ${animal.getAnimalType()} with bite type: ${animal.biteType}"
            is Cat -> "This is a cat of type: ${animal.getAnimalType()} with behavior: ${animal.behaviorType}"
            else -> "Unknown animal"
        }
    }
}