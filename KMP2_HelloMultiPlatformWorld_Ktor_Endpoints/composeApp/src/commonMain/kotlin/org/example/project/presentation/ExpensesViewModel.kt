package org.example.project.presentation

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.koin.core.KoinApplication.Companion.init

sealed class ExpensesUiState {
    data object Loading : ExpensesUiState()
    data class Success(val expenses: List<Expense>, val total: Double) : ExpensesUiState()
    data class Error(val message: String) : ExpensesUiState()
}

class ExpensesViewModel(private val repo: ExpenseRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getExpenseList()
    }

    private fun getExpenseList() {
        viewModelScope.launch {
            try {
                delay(2000)
                val expenses = repo.getAllExpenses()
                _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })

            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Error happenned")
            }
        }
    }

    private fun updateExpenseList() {
        viewModelScope.launch {
            try {
                val expenses = repo.getAllExpenses()
                _uiState.value = ExpensesUiState.Success(expenses, expenses.sumOf { it.amount })

            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Error happenned")
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                repo.addExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Error happenned")
            }
        }
    }

    fun editExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                repo.editExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Error happenned")
            }
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                repo.deleteExpense(expense)
                updateExpenseList()
            } catch (e: Exception) {
                _uiState.value = ExpensesUiState.Error(e.message ?: "Error happenned")
            }
        }
    }

    fun getExpenseWithId(id: Long): Expense? {
        return (_uiState.value as? ExpensesUiState.Success)?.expenses?.firstOrNull { it.id == id }
    }

    fun getCategories(): List<ExpenseCategory> {
        return repo.getCategories()
    }
}