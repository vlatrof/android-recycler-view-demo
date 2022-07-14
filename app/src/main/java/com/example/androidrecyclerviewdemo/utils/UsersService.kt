package com.example.androidrecyclerviewdemo.utils

import com.example.androidrecyclerviewdemo.model.User
import com.github.javafaker.Faker
import java.util.*
import kotlin.collections.ArrayList

class UsersService {

    private var users = mutableListOf<User>()
    private val listeners = mutableSetOf<UsersServiceListener>()

    init {

        val faker = Faker.instance()

        IMAGES.shuffle()

        users = (1..100).map {

            User(id = it.toLong(),
                 name = faker.name().name(),
                 company = faker.company().name(),
                 photo = IMAGES[it % IMAGES.size])

        }.toMutableList()

    }

    fun deleteUser(user: User) {

        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete == -1) return // user not found

        // creating new list for comparing in recycler view adapter
        users = ArrayList(users)
        users.removeAt(indexToDelete)
        notifyChanges()

    }

    fun fireUser(user: User) {

        val indexUserToFire = users.indexOfFirst { it.id == user.id }
        if (indexUserToFire == -1) return // user not found

        // creating new user for comparing in recyclerview adapter
        val updatedUser = user.copy(company = "")

        // creating new list for comparing in recyclerview adapter
        users = ArrayList(users)

        users[indexUserToFire] = updatedUser
        notifyChanges()

    }

    fun moveUser(user: User, moveBy: Int) {

        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return // user not found

        val newIndex = oldIndex - moveBy // move up = decrease index
        if (newIndex < 0 || newIndex >= users.size) return // new user position out of bounds

        // creating new list for comparing in recycler view adapter
        users = ArrayList(users)
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()

    }

    fun addListener(listener: UsersServiceListener) {
        listeners.add(listener)
        listener.onUsersUpdated(users)
    }

    fun removeListener(listener: UsersServiceListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { listener ->
            listener.onUsersUpdated(users)
        }
    }

    companion object {

        private val IMAGES = mutableListOf(
            "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cGVvcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cGVvcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8cGVvcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1534528741775-53994a69daeb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8cGVvcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1488161628813-04466f872be2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTl8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjF8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60",
            "https://images.unsplash.com/photo-1537511446984-935f663eb1f4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mjh8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"
        )

    }

}