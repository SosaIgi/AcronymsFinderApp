package com.tes.apps.development.acronymsfinderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.tes.apps.development.acronymsfinderapp.ui.home.AcronymFinderScreen
import com.tes.apps.development.acronymsfinderapp.ui.home.AcronymViewModel
import com.tes.apps.development.acronymsfinderapp.ui.theme.AcronymsFinderAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcronymsFinderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Acronyms()
                   // Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Acronyms(){
    val navController = rememberNavController()

    val   acronymViewModel: AcronymViewModel = hiltViewModel()
    AcronymFinderScreen(navController =navController, viewModel = acronymViewModel )

}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AcronymsFinderAppTheme {
        Greeting("Android")
    }
}