package com.example.jobboardmobilesecondapp.data.remote

import java.util.Date

data class Vacancy(
    val id: Int = 0,
    val title: String = "",
    val location: String = "Russia",
    val description: String = "",
    val salary: String = "",
    val workBase: String = "OnSite",
    val timestamp: Date = Date(),
    val firebaseId: String = ""
)
