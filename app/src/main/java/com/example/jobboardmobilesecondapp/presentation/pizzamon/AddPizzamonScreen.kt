package com.example.jobboardmobilesecondapp.presentation.pizzamon

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
import com.example.jobboardmobilesecondapp.data.remote.Pizzamon
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPizzamonScreen(navController: NavController){

    val pizzamonViewModel : PizzamonViewModel = viewModel()

    var name = remember { mutableStateOf("") }
    var type1 = remember { mutableStateOf("") }
    var type2 = remember { mutableStateOf("") }
    var description = remember { mutableStateOf("") }
    var health = remember { mutableStateOf("0") }
    var attack = remember { mutableStateOf("0") }
    var defence = remember { mutableStateOf("0") }
    var speed = remember { mutableStateOf("0") }

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
            text = "Name",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = name.value,
            onValueChange = {name.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Pizzamen") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Description",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = description.value,
            onValueChange = {description.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "your description") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "First type",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = type1.value,
            onValueChange = {type1.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "select your type") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Second type",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = type2.value,
            onValueChange = {type2.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "your second type (optional)") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Health stat",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = health.value,
            onValueChange = {health.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Health points") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Attack stat",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = attack.value,
            onValueChange = {attack.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Attack points") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Defence",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = defence.value,
            onValueChange = {defence.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Defence points") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Speed",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            value = speed.value,
            onValueChange = {speed.value = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Speed points") }
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            modifier = Modifier
                .height(56.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            onClick = {
                pizzamonViewModel.addPizzamon(pizzamon =
                Pizzamon(
                    name = name.value,
                    description = description.value,
                    type1 = type1.value,
                    type2 = type2.value,
                    health = health.value,
                    attack = attack.value,
                    defence = defence.value,
                    speed = speed.value,
                )

                ) {
                    navController.navigate(route = "ListPizzamonScreen")
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