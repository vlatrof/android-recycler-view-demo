package com.example.androidrecyclerviewdemo.utils

import com.example.androidrecyclerviewdemo.model.User

interface UsersServiceListener {
    fun onUsersUpdated(newUsersList: List<User>)
}