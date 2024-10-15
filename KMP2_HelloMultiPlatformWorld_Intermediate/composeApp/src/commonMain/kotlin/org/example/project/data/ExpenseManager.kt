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
            amount = 85.50,
            category = ExpenseCategory.COFFEE,
            description = "Starbucks Latte"
        ),
        Expense(
            id = currentId++,
            amount = 165.30,
            category = ExpenseCategory.SNACKS,
            description = "Oxxo"
        ),
        Expense(
            id = currentId++,
            amount = 2501345.65,
            category = ExpenseCategory.CAR,
            description = "Audi A1"
        ),
        Expense(
            id = currentId++,
            amount = 248.0,
            category = ExpenseCategory.PARTY,
            description = "Birthday"
        ),
        Expense(
            id = currentId++,
            amount = 15.50,
            category = ExpenseCategory.HOUSE,
            description = "Cleaning"
        ),
        Expense(
            id = currentId++,
            amount = 456.50,
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