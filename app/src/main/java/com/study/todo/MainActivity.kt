package com.study.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.study.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
data class Example(
    val id: Int? = null,
    val message: String = "Hello World"
)

interface ExampleRepository {
    fun getMessage(): List<Example>
}

class FakeExampleRepository : ExampleRepository {
    override fun getMessage(): List<Example> {
        return listOf(
            Example(message = "Hello World #1"),
            Example(message = "Hello World #2"),
            Example(message = "Hello World #3"),
            Example(message = "Hello World #4"),
            Example(message = "Hello World #5"),
            Example(message = "Hello World #6"),
            Example(message = "Hello World #7"),
            Example(message = "Hello World #8"),
            Example(message = "Hello World #9"),
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesExampleRepository(): ExampleRepository {
        return FakeExampleRepository()
    }
}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoTheme {
        Greeting("Android")
    }
}