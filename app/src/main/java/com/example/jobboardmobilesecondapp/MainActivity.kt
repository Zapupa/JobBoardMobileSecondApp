package com.example.jobboardmobilesecondapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jobboardmobilesecondapp.presentation.authorization.AuthorizationScreen
import com.example.jobboardmobilesecondapp.presentation.vacancy.AddVacancyScreen
import com.example.jobboardmobilesecondapp.presentation.vacancy.ListVacancyScreen
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

    NavHost(navController = navController, startDestination = "AuthorizationScreen" ) {
        composable(route = "AuthorizationScreen") {
            AuthorizationScreen(navController = navController)
        }

        composable(route = "ListVacancyScreen") {
           ListVacancyScreen(navController = navController)
        }

        composable(route = "AddVacancyScreen"){
            AddVacancyScreen(navController = navController)
        }
    }
}

