package com.example.androidrecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidrecyclerviewdemo.adapter.UsersAdapter
import com.example.androidrecyclerviewdemo.databinding.ActivityMainBinding
import com.example.androidrecyclerviewdemo.model.User
import com.example.androidrecyclerviewdemo.utils.UsersService
import com.example.androidrecyclerviewdemo.utils.App
import com.example.androidrecyclerviewdemo.utils.UsersServiceListener

class MainActivity : AppCompatActivity(), UsersServiceListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var usersAdapter: UsersAdapter

    private val usersService: UsersService
        get() = (applicationContext as App).usersService

    override fun onUsersUpdated(newUsersList: List<User>) {
        usersAdapter.users = newUsersList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersAdapter = UsersAdapter()
        binding.rvUsers.adapter = usersAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        usersService.addListener(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(this)
    }

}