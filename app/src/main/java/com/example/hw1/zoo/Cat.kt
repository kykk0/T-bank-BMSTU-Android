package com.example.hw1.zoo

interface Cat : Animal {
    val behaviorType: BehaviorType
}

enum class BehaviorType {
    ACTIVE, PASSIVE
}