package com.example.androidrecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidrecyclerviewdemo.adapter.UsersAdapter
import com.example.androidrecyclerviewdemo.databinding.ActivityMainBinding
import com.example.androidrecyclerviewdemo.model.User
import com.example.androidrecyclerviewdemo.utils.UsersService
import com.example.androidrecyclerviewdemo.utils.App
import com.example.androidrecyclerviewdemo.utils.UsersServiceListener

class MainActivity : AppCompatActivity(), UsersServiceListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usersService: UsersService
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usersService = (applicationContext as App).usersService
        usersAdapter = UsersAdapter()
        binding.rvUsers.adapter = usersAdapter
        usersService.addListener(this)


    }

    override fun onUsersUpdated(newUsersList: List<User>) {
        usersAdapter.users = newUsersList
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(this)
    }

}