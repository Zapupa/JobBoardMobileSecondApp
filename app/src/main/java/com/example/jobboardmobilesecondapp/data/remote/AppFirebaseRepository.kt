package com.example.jobboardmobilesecondapp.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.jobboardmobilesecondapp.data.DatabaseRepository
import com.example.jobboardmobilesecondapp.utils.Constants
import com.example.jobboardmobilesecondapp.utils.FIREBASE_ID
import com.example.jobboardmobilesecondapp.utils.LOGIN
import com.example.jobboardmobilesecondapp.utils.PASSWORD
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class AppFirebaseRepository() : DatabaseRepository {

    private val fAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(fAuth.currentUser?.uid.toString())



    override val readAll: LiveData<List<Vacancy>> = AllVacancyLiveData()

    override suspend fun create(vacancy: Vacancy, onSuccess: () -> Unit) {
        val vacancyId = database.push().key.toString()
        val mapVacancies = hashMapOf<String,Any>()

        mapVacancies[FIREBASE_ID] = vacancyId
        mapVacancies[Constants.Keys.TITLE] = vacancy.title
        mapVacancies[Constants.Keys.DESCRIPTION] = vacancy.description
        mapVacancies[Constants.Keys.LOCATION] = vacancy.location
        mapVacancies[Constants.Keys.WORKBASE] = vacancy.workBase
        mapVacancies[Constants.Keys.SALARY] = vacancy.salary
        mapVacancies[Constants.Keys.TIMESTAMP] = vacancy.timestamp

        database.child(vacancyId)
            .updateChildren(mapVacancies)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkFail","Failed to create vacancy") }
    }

    override suspend fun update(vacancy: Vacancy, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(vacancy: Vacancy, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        fAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                fAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }


}