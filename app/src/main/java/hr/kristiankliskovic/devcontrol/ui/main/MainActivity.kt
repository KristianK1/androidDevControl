package hr.kristiankliskovic.devcontrol.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hr.kristiankliskovic.devcontrol.ui.theme.DevControlTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevControlTheme {
                MainRoute(
                    viewModel = get()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DevControlTheme {
        MainRoute(
            viewModel = get()
        )
    }
}
