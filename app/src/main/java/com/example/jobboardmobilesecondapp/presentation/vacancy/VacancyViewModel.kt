package com.example.jobboardmobilesecondapp.presentation.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobboardmobilesecondapp.data.DatabaseRepository
import com.example.jobboardmobilesecondapp.data.remote.AppFirebaseRepository
import com.example.jobboardmobilesecondapp.data.remote.Vacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VacancyViewModel: ViewModel() {
    val repositoryFireBase: DatabaseRepository = AppFirebaseRepository()

    fun addVacancy(vacancy: Vacancy, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            repositoryFireBase.create(vacancy = vacancy){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }
}