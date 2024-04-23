package com.example.jobboardmobilesecondapp.presentation.vacancy

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.jobboardmobilesecondapp.data.remote.Vacancy

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListVacancyScreen(navController: NavHostController){
    val listVacancyViewModel : VacancyViewModel = viewModel()

    val vacancies = listVacancyViewModel.repositoryFireBase.readAll.observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = "AddVacancyScreen")
                },
                containerColor = Color.Yellow
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.Black
                )
            }
        }
    ) {
        LazyColumn(){
            items(vacancies) {vacancy ->
                VacancyItem(vacancy = vacancy, navController = navController)
            }
        }
    }
}

@Composable
fun VacancyItem(vacancy: Vacancy, navController: NavController) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("vacancy" + "/${vacancy.firebaseId}")
            },
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = vacancy.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.75f)
                )
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = vacancy.timestamp.toString(),
                    fontSize = 20.sp
                )
            }
            Text(
                text = vacancy.location,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = vacancy.description,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    Text(text = "$" + vacancy.salary,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 30.dp))
                }

                Card(
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    Text(text = vacancy.workBase,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 30.dp)
                    )
                }
            }
        }
    }
}