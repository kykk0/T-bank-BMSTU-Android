package com.example.hw1.zoo

interface Dog : Animal {
    val biteType: BiteType
}

enum class BiteType {
    STRAIGHT, OVERBITE, UNDERBITE
}