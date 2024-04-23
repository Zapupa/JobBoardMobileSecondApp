package com.example.jobboardmobilesecondapp.data.remote

enum class Location {
    Russia,
    Spain,
    USA
}

fun getLocationList() : List<Location> {
    val locationList = mutableListOf<Location>()
    locationList.add(Location.Russia)
    locationList.add(Location.Spain)
    locationList.add(Location.USA)

    return locationList
}