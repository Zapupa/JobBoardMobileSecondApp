package com.example.jobboardmobilesecondapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobboardmobilesecondapp.presentation.authorization.AuthorizationScreen
import com.example.jobboardmobilesecondapp.presentation.pizzamon.AddPizzamonScreen
import com.example.jobboardmobilesecondapp.presentation.pizzamon.ListPizzamonScreen
import com.example.jobboardmobilesecondapp.ui.theme.JobBoardMobileSecondAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobBoardMobileSecondAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JobBoardMobileApp()
                }
            }
        }
    }
}

@Composable
private fun JobBoardMobileApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ListPizzamonScreen" ) {
        composable(route = "AuthorizationScreen") {
            AuthorizationScreen(navController = navController)
        }

        composable(route = "ListPizzamonScreen") {
            ListPizzamonScreen(navController = navController)
        }

        composable(route = "AddPizzamonScreen"){
            AddPizzamonScreen(navController = navController)
        }
    }
}

