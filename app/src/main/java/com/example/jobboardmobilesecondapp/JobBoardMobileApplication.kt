package com.example.jobboardmobilesecondapp

import android.app.Application
import android.content.Context

class JobBoardMobileApplication : Application() {

    init {
        app = this

    }

    companion object {
        private lateinit var app: JobBoardMobileApplication

        fun getAppContext() : Context =
            app.applicationContext
    }
}