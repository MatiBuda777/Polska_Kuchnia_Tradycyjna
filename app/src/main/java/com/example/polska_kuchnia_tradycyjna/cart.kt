package com.example.polska_kuchnia_tradycyjna

object cart {
    private val meals = mutableListOf<Meal>()
    var currentMeal: Meal? = null

    fun addMeal(meal: Meal) {
        meals.add(meal)
    }

    fun getTotalPrice(): Int = meals.sumOf { it.price }
}