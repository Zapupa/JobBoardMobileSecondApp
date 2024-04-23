package com.example.jobboardmobilesecondapp.data

import androidx.lifecycle.LiveData
import com.example.jobboardmobilesecondapp.data.remote.Vacancy

interface DatabaseRepository {

    val readAll: LiveData<List<Vacancy>>

    suspend fun create (vacancy: Vacancy, onSuccess: () -> Unit)

    suspend fun update(vacancy: Vacancy, onSuccess: () -> Unit)

    suspend fun delete(vacancy: Vacancy, onSuccess: () -> Unit)

    fun singOut() { }

    fun connectToDatabase(onSuccess: () -> Unit, onFail:(String) -> Unit)
}