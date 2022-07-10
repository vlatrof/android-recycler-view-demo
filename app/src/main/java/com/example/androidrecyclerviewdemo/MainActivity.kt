package com.example.androidrecyclerviewdemo

import android.location.GnssAntennaInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidrecyclerviewdemo.adapter.UsersAdapter
import com.example.androidrecyclerviewdemo.databinding.ActivityMainBinding
import com.example.androidrecyclerviewdemo.model.User
import com.example.androidrecyclerviewdemo.model.UsersListener
import com.example.androidrecyclerviewdemo.model.UsersService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var usersAdapter: UsersAdapter

    private val usersService: UsersService
        get() = (applicationContext as App).usersService

    private val usersListener: UsersListener = {
        usersAdapter.users = it
    }

    fun onUsersUpdate(newUsersList: List<User>) {
        usersAdapter.users = newUsersList
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersAdapter = UsersAdapter()
        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        usersService.addListener(usersListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(usersListener)
    }

}