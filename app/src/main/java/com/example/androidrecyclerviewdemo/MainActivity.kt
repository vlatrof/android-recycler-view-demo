package com.example.androidrecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrecyclerviewdemo.adapter.UserListAdapter
import com.example.androidrecyclerviewdemo.databinding.ActivityMainBinding
import com.example.androidrecyclerviewdemo.model.User

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserListAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvUsersList
        adapter = UserListAdapter()
        adapter.setList(generateUserData())
        recyclerView.adapter = adapter

    }

    fun generateUserData(): ArrayList<User> {

        val userList = ArrayList<User>()

        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("Looking for job! =)", "Thank you for visiting my github!"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))
        userList.add(User("User_card_name_demo", "User_card_description_demo"))

        return userList

    }

}