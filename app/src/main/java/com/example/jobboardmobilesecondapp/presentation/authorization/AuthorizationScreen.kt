package com.example.jobboardmobilesecondapp.presentation.authorization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jobboardmobilesecondapp.R
import com.example.jobboardmobilesecondapp.utils.LOGIN
import com.example.jobboardmobilesecondapp.utils.PASSWORD
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun AuthorizationScreen(navController: NavHostController) {
    val authorizationViewModel: AuthorizationViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    var shouldShowAuthorizationWindow = remember { mutableStateOf(false) }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.bege))
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Pizzamon",
                fontWeight = FontWeight.Bold,
                fontSize = 60.sp,
                textAlign = TextAlign.Center,
                lineHeight = 50.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Create ypur unique Pizzamon and eat 'em all",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    shouldShowAuthorizationWindow.value = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "pizza time")

                if(shouldShowAuthorizationWindow.value) {
                    AlertDialog(
                        onDismissRequest = {
                            shouldShowAuthorizationWindow.value = true
                        },
                        title = {
                            Text(
                                text = "Log in",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) },
                        confirmButton = {
                            OutlinedTextField(
                                value = login,
                                onValueChange = { login = it },
                                label = { Text(text = "login") },
                                isError = login.isEmpty()
                            )
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text(text = "password") },
                                isError = password.isEmpty()
                            )
                            Button(
                                enabled = login.isNotEmpty() && password.isNotEmpty(),
                                modifier = Modifier.padding(top = 16.dp),
                                colors = ButtonDefaults.buttonColors(contentColor = colorResource(id = R.color.white)),
                                onClick = {
                                    LOGIN = login
                                    PASSWORD = password
                                    coroutineScope.launch {
                                        authorizationViewModel.initDatabase("Firebase") {
                                            navController.navigate(route = "ListPizzamonScreen")
                                        }
                                        shouldShowAuthorizationWindow.value = false
                                    }
                                }) {
                                Text(text = "Sign in")
                            }

                        })
                }
            }
        }
    }
}