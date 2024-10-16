package org.example.project.data

import org.example.project.AppDatabase
import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

class ExpenseRepoImpl(
    //private val expenseManager: ExpenseManager,
    private val appDatabase: AppDatabase
) : ExpenseRepository {

    private val queries = appDatabase.expensesDbQueries

    override fun getAllExpenses(): List<Expense> {
        // * From Database
        return queries.selectAll().executeAsList().map {
            Expense(
                id = it.id,
                amount = it.amount,
                description = it.description,
                category = ExpenseCategory.valueOf(it.categoryName)
            )
        }

        // * From Mock data
        //return expenseManager.fakeExpenseList
    }

    override fun addExpense(expense: Expense) {
        // * From Database
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                description = expense.description,
                categoryName = expense.category.name
            )
        }

        // * From Mock data
        //expenseManager.addExpense(expense)
    }

    override fun editExpense(expense: Expense) {
        // * From Database
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                description = expense.description,
                categoryName = expense.category.name
            )
        }

        // * From Mock data
        //expenseManager.editExpense(expense)
    }

    override fun deleteExpense(expense: Expense): List<Expense> {
        // * From Database
        queries.transaction {
            queries.delete(
                id = expense.id
            )
        }

        return getAllExpenses()

        // * From Mock data
        //expenseManager.deleteExpense(expense)
        //return expenseManager.fakeExpenseList
    }

    override fun getCategories(): List<ExpenseCategory> {
        // * From Database
        return queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }

        // * From Mock data
        //return expenseManager.getCategories()
    }
}