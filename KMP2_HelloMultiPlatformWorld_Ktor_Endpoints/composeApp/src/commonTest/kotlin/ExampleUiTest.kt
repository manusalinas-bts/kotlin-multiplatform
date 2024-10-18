import androidx.compose.ui.test.ExperimentalTestApi
import kotlin.test.Test
import androidx.compose.ui.test.runComposeUiTest

class ExampleUiTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun myTest() = runComposeUiTest {
        setContent {

        }
    }
}