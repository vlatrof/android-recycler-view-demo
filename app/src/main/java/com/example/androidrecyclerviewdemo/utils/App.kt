package com.example.androidrecyclerviewdemo.utils

import android.app.Application
import android.content.Context

class App : Application() {

    val context: Context = applicationContext
    val usersService = UsersService()

}