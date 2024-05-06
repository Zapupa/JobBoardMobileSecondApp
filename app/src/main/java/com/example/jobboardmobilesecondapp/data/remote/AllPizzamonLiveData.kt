package com.example.jobboardmobilesecondapp.data.remote

import androidx.lifecycle.LiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class AllPizzamonLiveData : LiveData<List<Pizzamon>>() {

    private val fAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(fAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            val pizzamons = mutableListOf<Pizzamon>()
            snapshot.children.map {
                pizzamons.add(it.getValue(Pizzamon::class.java) ?: Pizzamon())
            }
            value = pizzamons
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }

    override fun onActive() {
        database.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        database.removeEventListener(listener)
        super.onInactive()
    }

}