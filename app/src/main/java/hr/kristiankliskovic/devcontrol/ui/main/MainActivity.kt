package hr.kristiankliskovic.devcontrol.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hr.kristiankliskovic.devcontrol.ui.theme.DevControlTheme

class MainActivity : ComponentActivity() {

    lateinit var viewModel: MainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DevControlTheme {
                MainScreen()
            }
        }
    }

    override fun onPause() {
        Log.i("sviki", "onPause")
//        viewModel.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
//        viewModel.onResume()
        Log.i("sviki", "onResume")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DevControlTheme {
        MainScreen()
    }
}
