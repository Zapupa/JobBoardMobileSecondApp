package com.example.jobboardmobilesecondapp.presentation.authorization

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jobboardmobilesecondapp.data.remote.AppFirebaseRepository

class AuthorizationViewModel : ViewModel() {

    fun initDatabase(type:String, onSuccess: () -> Unit) {
        Log.d("checkInit","initDatabase work")
        val repository = AppFirebaseRepository()
        repository.connectToDatabase(
            { onSuccess() },
            { Log.d("checkInit","Error $it")}
        )
    }

}