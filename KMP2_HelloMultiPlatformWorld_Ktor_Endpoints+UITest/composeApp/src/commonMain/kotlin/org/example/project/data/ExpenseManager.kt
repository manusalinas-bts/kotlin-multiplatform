package org.example.project.data

import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

object ExpenseManager {

    private var currentId = 1L

    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentId++,
            amount = 70.0,
            category = ExpenseCategory.GROCERIES,
            description = "Weekly Buy"
        ),
        Expense(
            id = currentId++,
            amount = 85.0,
            category = ExpenseCategory.COFFEE,
            description = "Starbucks Latte"
        ),
        Expense(
            id = currentId++,
            amount = 165.0,
            category = ExpenseCategory.SNACKS,
            description = "Oxxo"
        ),
        Expense(
            id = currentId++,
            amount = 2500.0,
            category = ExpenseCategory.CAR,
            description = "Jeep"
        ),
        Expense(
            id = currentId++,
            amount = 240.0,
            category = ExpenseCategory.PARTY,
            description = "Birthday"
        ),
        Expense(
            id = currentId++,
            amount = 15.0,
            category = ExpenseCategory.HOUSE,
            description = "Cleaning"
        ),
        Expense(
            id = currentId++,
            amount = 450.0,
            category = ExpenseCategory.OTHER,
            description = "Services"
        )
    )

    fun addExpense(expense: Expense) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }

        if (index != -1) {
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }

    fun deleteExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }

        if (index != -1) {
            fakeExpenseList.removeAt(index)
        }
    }

    fun getCategories(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.PARTY,
            ExpenseCategory.SNACKS,
            ExpenseCategory.COFFEE,
            ExpenseCategory.CAR,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER,
        )
    }
}