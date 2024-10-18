import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/**
 * Dependencies on 'build.gradle.kts' file
commonTest.dependencies {
implementation(libs.kotlin.test)
}
 */

class ExampleTest {
    @Test
    fun sumSuccessTest() {
        // Given
        val x = 5
        val y = 10

        // When
        val result = x + y

        // Then
        assertEquals(15, result, "Expected result")
    }

    @Test
    fun sumUnexpectedTest() {
        // Given
        val x = 5
        val y = 10

        // When
        val result = x + y

        // Then
        assertNotEquals(10, result, "Unecpected result")
    }

    @Test
    fun expenseModelListTest() {
        // Given
        val expense = Expense(id = 1, amount = 21.0, category = ExpenseCategory.COFFEE, description = "Latte")
        val expenseList = mutableListOf<Expense>()

        // When
        expenseList.add(expense)

        // Then
        assertContains(expenseList, expense)
    }

    @Test
    fun expenseModelParamTestSuccess() {
        // Given
        val expenseA = Expense(id = 1, amount = 21.0, category = ExpenseCategory.COFFEE, description = "Latte")
        val expenseB = Expense(id = 1, amount = 21.0, category = ExpenseCategory.COFFEE, description = "Latte")
        val expenseList = mutableListOf<Expense>()

        // When
        expenseList.add(expenseA)
        expenseList.add(expenseB)

        // Then
        assertEquals(expenseA.category, expenseB.category)
    }
}


