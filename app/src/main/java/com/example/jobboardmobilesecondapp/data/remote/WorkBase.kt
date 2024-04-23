package com.example.jobboardmobilesecondapp.data.remote

enum class WorkBase {
    OnSite,
    Remote
}

fun getWorkBaseList() : List<WorkBase> {
    val workbaseList = mutableListOf<WorkBase>()
    workbaseList.add(WorkBase.OnSite)
    workbaseList.add(WorkBase.Remote)

    return  workbaseList
}