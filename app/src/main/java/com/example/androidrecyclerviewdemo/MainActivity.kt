package com.example.androidrecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidrecyclerviewdemo.adapter.UserActionListener
import com.example.androidrecyclerviewdemo.adapter.UsersAdapter
import com.example.androidrecyclerviewdemo.databinding.ActivityMainBinding
import com.example.androidrecyclerviewdemo.model.User
import com.example.androidrecyclerviewdemo.utils.UsersService
import com.example.androidrecyclerviewdemo.utils.App
import com.example.androidrecyclerviewdemo.utils.UsersServiceListener

class MainActivity : AppCompatActivity(), UsersServiceListener {

    private val usersService: UsersService
        get() = (applicationContext as App).usersService

    private lateinit var binding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersAdapter = UsersAdapter(object: UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
                usersService.moveUser(user, moveBy)
            }
            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }
            override fun onUserDetails( user: User) {
                Toast.makeText(this@MainActivity, user.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        binding.rvUsers.adapter = usersAdapter
        usersService.addListener(this)

    }

    override fun onUsersUpdated(newUsersList: List<User>) {
        usersAdapter.setData(newUsersList)
    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(this)
    }

}