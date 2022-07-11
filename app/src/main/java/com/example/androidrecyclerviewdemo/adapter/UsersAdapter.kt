package com.example.androidrecyclerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrecyclerviewdemo.R
import com.example.androidrecyclerviewdemo.databinding.ItemUserLayoutBinding
import com.example.androidrecyclerviewdemo.model.User

class UsersAdapter() : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    // DATA + SETTER + GETSIZE
    var users: List<User> = emptyList()
    fun setData(value: List<User>) {
        users = value
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = users.size

    // VIEW HOLDER
    class UsersViewHolder(
        val binding: ItemUserLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)

    // VIEW HOLDER CREATE EMPTY
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        val binding = ItemUserLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        binding.root.setOnClickListener {
            onUserDetails(it.context, it.tag as User)
        }

        binding.ivMoreButton.setOnClickListener {
            // todo
        }

        return UsersViewHolder(binding)

    }

    // VIEW HOLDER FILL AND BIND
    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {

        val user = users[position]

        val binding = holder.binding
        holder.itemView.tag = user
        binding.tvUserName.text = user.name
        binding.tvUserCompanyName.text = user.company
        binding.ivMoreButton.tag = user
        binding.ivMoreButton.setImageResource(R.drawable.ic_more)

        if (user.photo.isBlank()) {
            binding.ivUserPhoto.setImageResource(R.drawable.ic_user_photo_placeholder)
            return
        }

        Glide.with(binding.ivUserPhoto.context)
            .load(user.photo)
            .circleCrop()
            .placeholder(R.drawable.ic_user_photo_placeholder)
            .error(R.drawable.ic_user_photo_placeholder)
            .into(binding.ivUserPhoto)

    }

    fun onUserDetails(viewContext: Context, user: User) {
        Toast.makeText(viewContext, user.toString(), Toast.LENGTH_SHORT).show()
    }

    fun onUserMove(user: User, moveBy: Int) {
        TODO("Not yet implemented")
    }

    fun onUserDelete(user: User) {
        TODO("Not yet implemented")
    }

}
