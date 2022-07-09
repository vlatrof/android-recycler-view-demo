package com.example.androidrecyclerviewdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrecyclerviewdemo.R
import com.example.androidrecyclerviewdemo.databinding.ItemUserLayoutBinding
import com.example.androidrecyclerviewdemo.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    var users: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserLayoutBinding.inflate(
            inflater, parent, false)

        return UsersViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {

        val user = users[position]

        with(holder.binding) {

            tvUserName.text = user.name
            tvUserCompanyName.text = user.company

            if (user.photo.isNotBlank()) {
                Glide.with(ivUserPhoto.context)
                    .load(user.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_photo_placeholder)
                    .error(R.drawable.ic_user_photo_placeholder)
                    .into(ivUserPhoto)
            } else {
                ivUserPhoto.setImageResource(R.drawable.ic_user_photo_placeholder)
            }

        }
    }

    class UsersViewHolder(
        val binding: ItemUserLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)

}