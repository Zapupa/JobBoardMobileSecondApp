package com.example.jobboardmobilesecondapp.presentation.pizzamon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboardmobilesecondapp.data.DatabaseRepository
import com.example.jobboardmobilesecondapp.data.remote.AppFirebaseRepository
import com.example.jobboardmobilesecondapp.data.remote.Pizzamon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PizzamonViewModel: ViewModel() {
    val repositoryFireBase: DatabaseRepository = AppFirebaseRepository()

    fun addPizzamon(pizzamon: Pizzamon, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            repositoryFireBase.create(pizzamon = pizzamon){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }
}