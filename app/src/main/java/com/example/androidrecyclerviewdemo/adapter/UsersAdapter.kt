package com.example.androidrecyclerviewdemo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.androidrecyclerviewdemo.databinding.ItemUserLayoutBinding

class UsersAdapter : RecyclerView.Adapter<> {

    class UsersViewHolder(
        val binding: ItemUserLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)



}