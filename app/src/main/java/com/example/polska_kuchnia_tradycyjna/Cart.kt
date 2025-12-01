package com.example.polska_kuchnia_tradycyjna

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Cart {
    private val _orders = MutableLiveData<List<Meal>>(emptyList())
    val orders: LiveData<List<Meal>> get() = _orders

    private var _currentOrder = MutableLiveData<Meal>()
    val currentOrder: LiveData<Meal> get() = _currentOrder

    fun setCurrentOrder(meal: Meal) {
        _currentOrder.value = meal
    }

    fun addMeal(meal: Meal) {
        val currentList = _orders.value ?: emptyList()
        _orders.value = currentList + meal
    }

    fun getTotalPrice(): Int = _orders.value?.sumOf { it.price } ?: 0
}