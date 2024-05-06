package com.example.jobboardmobilesecondapp.presentation.pizzamon

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.jobboardmobilesecondapp.R
import com.example.jobboardmobilesecondapp.data.remote.Pizzamon

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPizzamonScreen(navController: NavHostController){
    val listVacancyViewModel : PizzamonViewModel = viewModel()

    val pizzamons = listVacancyViewModel.repositoryFireBase.readAll.observeAsState(listOf()).value

    Scaffold(
        modifier = Modifier.background(color = colorResource(id = R.color.bege)),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = "AddPizzamonScreen")
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
            items(pizzamons) {pizzamon ->
                PizzamonItem(pizzamon = pizzamon, navController = navController)
            }
        }
    }
}

@Composable
fun PizzamonItem(pizzamon: Pizzamon, navController: NavController) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("pizzamon" + "/${pizzamon.id}")
            },
        colors = CardDefaults.cardColors(Color.White),
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
                    text = pizzamon.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.75f)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = pizzamon.description,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Types:",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    Text(text = pizzamon.type1,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 30.dp))
                }

                Card(
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    Text(text = pizzamon.type2,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 30.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = "Stats:",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "HEALTH",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 20.sp
                )
                Text(
                    text = pizzamon.health,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "ATTACK",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 20.sp
                )
                Text(
                    text = pizzamon.attack,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "DEFENCE",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 20.sp
                )
                Text(
                    text = pizzamon.defence,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "SPEED",
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 20.sp
                )
                Text(
                    text = pizzamon.speed,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 20.sp
                )
            }
        }
    }
}