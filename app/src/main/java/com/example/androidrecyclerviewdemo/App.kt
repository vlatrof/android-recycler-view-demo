package com.example.androidrecyclerviewdemo

import android.app.Application
import com.example.androidrecyclerviewdemo.model.UsersService

class App : Application() {

    val usersService = UsersService()

}