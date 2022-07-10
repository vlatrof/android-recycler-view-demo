package com.example.androidrecyclerviewdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrecyclerviewdemo.R
import com.example.androidrecyclerviewdemo.databinding.ItemUserLayoutBinding
import com.example.androidrecyclerviewdemo.model.User
import com.example.androidrecyclerviewdemo.utils.UserActionListener

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(),
                     UserActionListener,
                     View.OnClickListener{

    var users: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class UsersViewHolder(
        val binding: ItemUserLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        val binding = ItemUserLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return UsersViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {

        val user = users[position]

        with(holder.binding) {

            tvUserName.text = user.name
            tvUserCompanyName.text = user.company
            ivMoreButton.setImageResource(R.drawable.ic_more)

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

    override fun onUserMove(user: User, moveBy: Int) {
        TODO("Not yet implemented")
    }

    override fun onUserDetails(user: User) {
        TODO("Not yet implemented")
    }

    override fun onUserDelete(user: User) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}