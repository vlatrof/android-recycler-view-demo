package com.example.androidrecyclerviewdemo.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrecyclerviewdemo.databinding.ItemUserLayoutBinding
import com.example.androidrecyclerviewdemo.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    var users: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class UsersViewHolder(
        val binding: ItemUserLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)

}