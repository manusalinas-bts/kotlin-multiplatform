package previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.data.ExpenseManager
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.presentation.ExpensesUiState
import org.example.project.ui.AllExpensesHeader
import org.example.project.ui.ExpensesItem
import org.example.project.ui.ExpensesScreen
import org.example.project.ui.ExpensesTotalHeader


@Preview(showBackground = true)
@Composable
fun ExpensesTotalHeaderPreview() {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        ExpensesTotalHeader(13345.67)
    }
}

@Preview(showBackground = true)
@Composable
fun AllExpensesPreview() {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        AllExpensesHeader()
    }
}

@Preview(showBackground = true)
@Composable
fun ExpensesItemPreview() {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        ExpensesItem(
            expense = ExpenseManager.fakeExpenseList[0]
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpensesScreenPreview() {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        ExpensesScreen(
            uiState = ExpensesUiState.Success(
                expenses = ExpenseManager.fakeExpenseList,
                total = 1234.45
            ),
            onExpenseClick = {},
            onDeleteExpense = {}
        )
    }
}