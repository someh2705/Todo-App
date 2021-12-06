package com.study.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.study.todo.ui.theme.TodoTheme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "example") {
        composable("example") {
            val exampleViewModel :ExampleViewModel = hiltViewModel()
            ExampleScreen(exampleViewModel)
        }
    }
}

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

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel() {
    private val _example: MutableLiveData<List<Example>> = MutableLiveData()
    val example: LiveData<List<Example>> = _example

    init {
        loadExample()
    }

    fun loadExample() {
        repository.getMessage().let {
            _example.postValue(it)
        }
    }
}

@Composable
fun ExampleScreen(
    exampleViewModel: ExampleViewModel = viewModel()
) {
    val data = exampleViewModel.example.observeAsState()

    data.value?.let {
        LazyColumn {
            items(it) { example ->
                Text(text = example.message)
            }
        }
    }
}

@Preview
@Composable
fun ExampleScreenPreview() {
    MaterialTheme {
        Surface {
            ExampleScreen()
        }
    }
}