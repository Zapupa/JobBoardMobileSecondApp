package com.example.jobboardmobilesecondapp.presentation.vacancy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jobboardmobilesecondapp.data.remote.Location
import com.example.jobboardmobilesecondapp.data.remote.Vacancy
import com.example.jobboardmobilesecondapp.data.remote.WorkBase
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVacancyScreen(navController: NavController){

    val vacancyViewModel : VacancyViewModel = viewModel()

    var title = remember { mutableStateOf("") }
    var workbase = remember { mutableStateOf(WorkBase.OnSite.name) }
    var location = remember { mutableStateOf(Location.Russia.name) }
    var startDate = rememberSaveable { mutableStateOf(Date()) }
    var description = remember { mutableStateOf("") }
    var salary = remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Add Vacancy",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Vacancy name",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = title.value,
            onValueChange = {title.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "e.g Software Engineer") }
        )

        Button(
            modifier = Modifier
                .height(56.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            onClick = {
                vacancyViewModel.addVacancy(vacancy =
                Vacancy(
                    title = title.value,
                    description = description.value,
                    location = location.value,
                    salary = salary.value,
                    workBase = workbase.value,
                    timestamp = startDate.value
                )

                ) {
                    navController.navigate(route = "ListVacancyScreen")
                }
            }
        ) {
            Text(
                text = "Save",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}