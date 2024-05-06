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



    override val readAll: LiveData<List<Pizzamon>> = AllPizzamonLiveData()

    override suspend fun create(pizzamon: Pizzamon, onSuccess: () -> Unit) {
        val pizzamonId = database.push().key.toString()
        val mapPizzamons = hashMapOf<String,Any>()

        mapPizzamons[FIREBASE_ID] = pizzamonId
        mapPizzamons[Constants.Keys.NAME] = pizzamon.name
        mapPizzamons[Constants.Keys.TYPE1] = pizzamon.type1
        mapPizzamons[Constants.Keys.TYPE2] = pizzamon.type2
        mapPizzamons[Constants.Keys.DESCRIPTION] = pizzamon.description
        mapPizzamons[Constants.Keys.HEALTH] = pizzamon.health
        mapPizzamons[Constants.Keys.ATTACK] = pizzamon.attack
        mapPizzamons[Constants.Keys.DEFENCE] = pizzamon.defence
        mapPizzamons[Constants.Keys.SPEED] = pizzamon.speed

        database.child(pizzamonId)
            .updateChildren(mapPizzamons)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkFail","Failed to create vacancy") }
    }

    override suspend fun update(pizzamon: Pizzamon, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(pizzamon: Pizzamon, onSuccess: () -> Unit) {
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