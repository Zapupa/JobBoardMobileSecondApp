package com.example.jobboardmobilesecondapp.data

import androidx.lifecycle.LiveData
import com.example.jobboardmobilesecondapp.data.remote.Pizzamon

interface DatabaseRepository {

    val readAll: LiveData<List<Pizzamon>>

    suspend fun create (pizzamon: Pizzamon, onSuccess: () -> Unit)

    suspend fun update(pizzamon: Pizzamon, onSuccess: () -> Unit)

    suspend fun delete(pizzamon: Pizzamon, onSuccess: () -> Unit)

    fun singOut() { }

    fun connectToDatabase(onSuccess: () -> Unit, onFail:(String) -> Unit)
}