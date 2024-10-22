package org.example.project.domain

import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

interface ExpenseRepository {
    suspend fun getAllExpenses(): List<Expense>
    suspend fun addExpense(expense: Expense)
    suspend fun editExpense(expense: Expense)
    suspend fun deleteExpense(id: Long)
    fun getCategories(): List<ExpenseCategory>
}