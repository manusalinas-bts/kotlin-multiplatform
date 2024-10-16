package com.example.data.model

import kotlinx.serialization.Serializable

val expenses = mutableListOf(
    Expense(id = 1, amount = 70.0, categoryName = "GROCERIES", description = "Weekly Buy"),
    Expense(id = 2, amount = 85.0, categoryName = "COFFEE", description = "Starbucks Latte"),
    Expense(id = 3, amount = 165.0, categoryName = "SNACKS", description = "Oxxo"),
    Expense(id = 4, amount = 2500.0, categoryName = "CAR", description = "Jeep"),
    Expense(id = 5, amount = 240.0, categoryName = "PARTY", description = "Birthday"),
    Expense(id = 6, amount = 15.0, categoryName = "HOUSE", description = "Cleaning"),
    Expense(id = 7, amount = 450.0, categoryName = "OTHER", description = "Services")
)

@Serializable
data class Expense(
    val id: Long = -1,
    val amount: Double,
    val categoryName: String,
    val description: String
)

