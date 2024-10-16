import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.waitUntilExactlyOneExists
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.presentation.ExpensesUiState
import org.example.project.ui.ExpensesDetailScreen
import org.example.project.ui.ExpensesScreen
import org.example.project.utils.EXPENSE_DETAIL_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_ERROR_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_ERROR_TEXT_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_LOADING_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_CLICK_ITEM_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_EMPTY_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_TEST_TAG
import org.example.project.utils.EXPENSE_SCREEN_SUCCESS_TOTAL_TEST_TAG
import kotlin.test.Test

class ExpenseScreenUiTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun ExpenseScreenLoadingTest() = runComposeUiTest {
        val loadingUiState = ExpensesUiState.Loading

        setContent {
            ExpensesScreen(uiState = loadingUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_LOADING_TEST_TAG).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun ExpenseScreenErrorTest() = runComposeUiTest {
        val errorText = "Error de carga"
        val assertErrorText = "Error $errorText"
        val errorUiState = ExpensesUiState.Error(message = errorText)

        setContent {
            ExpensesScreen(uiState = errorUiState, onExpenseClick = {}, onDeleteExpense = {})
        }

        onNodeWithTag(EXPENSE_SCREEN_ERROR_TEST_TAG).assertExists()
        onNodeWithTag(EXPENSE_SCREEN_ERROR_TEXT_TEST_TAG).assertExists(assertErrorText)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun ExpenseScreenSuccessTest() = runComposeUiTest {
        val successUiState = ExpensesUiState.Success(
            listOf(
                Expense(
                    id = 1,
                    amount = 20.0,
                    category = ExpenseCategory.COFFEE,
                    description = "Cola Loka"
                ),
                Expense(
                    id = 2,
                    amount = 500.0,
                    category = ExpenseCategory.CAR,
                    description = "VW"
                )
            ),
            total = 520.0
        )

        setContent {
            ExpensesScreen(uiState = successUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TEST_TAG).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun ExpenseScreenSuccessEmptyTest() = runComposeUiTest {
        val successEmptyUiState = ExpensesUiState.Success(emptyList(), total = 0.0)

        setContent {
            ExpensesScreen(uiState = successEmptyUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_EMPTY_TEST_TAG).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun ExpenseScreenSuccessTotalTest() = runComposeUiTest {
        val successTotalUiState = ExpensesUiState.Success(
            listOf(
                Expense(
                    id = 1,
                    amount = 20.0,
                    category = ExpenseCategory.COFFEE,
                    description = "Cola Loka"
                ),
                Expense(
                    id = 2,
                    amount = 500.0,
                    category = ExpenseCategory.CAR,
                    description = "VW"
                )
            ),
            total = 520.0
        )

        setContent {
            ExpensesScreen(uiState = successTotalUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TEST_TAG).assertExists()
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_TOTAL_TEST_TAG).assertTextEquals("$520.0")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun ExpenseScreenSuccessClickTest() = runComposeUiTest {
        val successClickUiState = ExpensesUiState.Success(
            listOf(
                Expense(
                    id = 1,
                    amount = 20.0,
                    category = ExpenseCategory.COFFEE,
                    description = "Cola Loka"
                ),
                Expense(
                    id = 2,
                    amount = 500.0,
                    category = ExpenseCategory.CAR,
                    description = "VW"
                )
            ),
            total = 520.0
        )

        setContent {
            PreComposeApp {
                val navigator = rememberNavigator()
                NavHost(
                    navigator = navigator,
                    initialRoute = "/home"
                ) {
                    scene(route = "/home") {
                        ExpensesScreen(
                            uiState = successClickUiState,
                            onExpenseClick = { expense ->
                                navigator.navigate("/addExpenses/${expense.id}")
                            },
                            onDeleteExpense = { _ -> }
                        )
                    }

                    scene("/addExpenses/{id}?") { backStackEntry ->
                        ExpensesDetailScreen(
                            expenseToEdit = successClickUiState.expenses[0],
                            categoryList = emptyList()
                        ) { _ -> }
                    }
                }
            }


            ExpensesScreen(uiState = successClickUiState, onExpenseClick = {}, onDeleteExpense = {})
        }
        onNodeWithTag(EXPENSE_SCREEN_SUCCESS_CLICK_ITEM_TEST_TAG.plus("_1")).performClick()
        waitUntilExactlyOneExists(hasTestTag(EXPENSE_DETAIL_TEST_TAG))
        onNodeWithTag(EXPENSE_DETAIL_TEST_TAG).assertExists()
    }
}