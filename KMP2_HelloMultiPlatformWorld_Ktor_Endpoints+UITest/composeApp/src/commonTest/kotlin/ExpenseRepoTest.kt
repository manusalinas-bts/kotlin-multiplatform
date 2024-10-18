import org.example.project.data.ExpenseManager
import org.example.project.data.ExpenseRepoImpl
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExpenseRepoTest {
    /*
    private val expenseManager = ExpenseManager
    private val repo = ExpenseRepoImpl(expenseManager)

    @Test
    fun expense_list_is_not_empty() {
        // GIVEN
        val expenseList = mutableListOf<Expense>()

        // WHEN
        expenseList.addAll(repo.getAllExpenses())

        // THEN
        assertTrue(expenseList.isNotEmpty())
    }

    @Test
    fun add_new_expense() {
        // GIVEN
        val expenseList = repo.getAllExpenses()
        val expense =
            Expense(amount = 21.0, category = ExpenseCategory.COFFEE, description = "Latte")

        // WHEN
        repo.addExpense(expense)

        // THEN
        assertContains(expenseList, expenseList.find { it.id == 7L })
    }

    @Test
    fun edit_new_expense() {
        // GIVEN
        val expenseListBefore = repo.getAllExpenses()
        val expense =
            Expense(amount = 21.0, category = ExpenseCategory.COFFEE, description = "Latte")

        // WHEN
        val newExoenseId = 7L
        repo.addExpense(expense)

        assertNotNull(expenseListBefore.find { it.id == 7L })

        val updatedExpense = Expense(
            id = newExoenseId,
            amount = 40.0,
            category = ExpenseCategory.CAR,
            description = "Jeep"
        )
        repo.editExpense(updatedExpense)

        // THEN
        val expenseListAfter = repo.getAllExpenses()
        assertEquals(updatedExpense, expenseListAfter.find { it.id == newExoenseId })
    }

    @Test
    fun get_all_categories() {
        // GIVEN
        val categoryList = mutableListOf<ExpenseCategory>()

        // WHEN
        categoryList.addAll(repo.getCategories())

        // THEN
        assertTrue(categoryList.isNotEmpty())
    }

    @Test
    fun check_all_categories() {
        // GIVEN
        val allCategories = listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.PARTY,
            ExpenseCategory.SNACKS,
            ExpenseCategory.COFFEE,
            ExpenseCategory.CAR,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER,
        )

        // WHEN
        val repoCategories =  repo.getCategories()

        // THEN
        assertEquals(allCategories, repoCategories)
    }
    */
}