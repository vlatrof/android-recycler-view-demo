package com.example.androidrecyclerviewdemo.utils

import com.example.androidrecyclerviewdemo.model.User

interface UserActionListener {

    fun onUserMove(user: User, moveBy: Int)
    fun onUserDetails(user: User)
    fun onUserDelete(user: User)

}