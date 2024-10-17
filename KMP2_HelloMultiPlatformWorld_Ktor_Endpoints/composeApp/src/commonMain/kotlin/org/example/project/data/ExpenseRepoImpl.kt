package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.project.AppDatabase
import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.model.NetworkExpense

private const val BASE_URL = "http://192.168.68.112:8080" // Obtain this from your Wifi Details

class ExpenseRepoImpl(
    private val appDatabase: AppDatabase,
    private val httpClient: HttpClient
) : ExpenseRepository {

    private val queries = appDatabase.expensesDbQueries

    override suspend fun getAllExpenses(): List<Expense> {
        // 1. Get server info
        // 2. Store remote info on local DB
        // 3. Use local info from DB
        return if (queries.selectAll().executeAsList().isEmpty()) {
            val response = httpClient.get("$BASE_URL/expenses").body<List<NetworkExpense>>()
            val expenses = response.map { networkExpense ->
                Expense(
                    id = networkExpense.id,
                    amount = networkExpense.amount,
                    description = networkExpense.description,
                    category = ExpenseCategory.valueOf(networkExpense.categoryName)
                )
            }

            expenses.forEach {
                queries.insert(
                    it.amount,
                    it.category.name,
                    it.description
                )
            }
            expenses
        } else {
            // * From Database
            queries.selectAll().executeAsList().map {
                Expense(
                    id = it.id,
                    amount = it.amount,
                    description = it.description,
                    category = ExpenseCategory.valueOf(it.categoryName)
                )
            }
        }
    }

    override suspend fun addExpense(expense: Expense) {
        // * From Database
        queries.transaction {
            queries.insert(
                amount = expense.amount,
                description = expense.description,
                categoryName = expense.category.name
            )
        }

        httpClient.post("$BASE_URL/expenses") {
            contentType(ContentType.Application.Json)
            setBody(
                NetworkExpense(
                    amount = expense.amount,
                    description = expense.description,
                    categoryName = expense.category.name
                )
            )
        }
    }

    override suspend fun editExpense(expense: Expense) {
        // * From Database
        queries.transaction {
            queries.update(
                id = expense.id,
                amount = expense.amount,
                description = expense.description,
                categoryName = expense.category.name
            )
        }

        httpClient.put("$BASE_URL/expenses/${expense.id}") {
            contentType(ContentType.Application.Json)
            setBody(
                NetworkExpense(
                    id = expense.id,
                    amount = expense.amount,
                    description = expense.description,
                    categoryName = expense.category.name
                )
            )
        }
    }

    override suspend fun deleteExpense(expense: Expense): List<Expense> {
        // * From Database
        queries.transaction {
            queries.delete(
                id = expense.id
            )
        }

        return getAllExpenses()
    }

    override fun getCategories(): List<ExpenseCategory> {
        // * From Database
        return queries.categories().executeAsList().map {
            ExpenseCategory.valueOf(it)
        }
    }
}