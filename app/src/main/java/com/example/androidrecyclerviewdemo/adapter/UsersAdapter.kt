package com.example.androidrecyclerviewdemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrecyclerviewdemo.R
import com.example.androidrecyclerviewdemo.databinding.ItemUserLayoutBinding
import com.example.androidrecyclerviewdemo.model.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private val POPUN_MENU_ID_MOVE_UP = 1
    private val POPUN_MENU_ID_MOVE_DOWN = 2
    private val POPUN_MENU_ID_DELETE_USER = 3

    var users: List<User> = emptyList()
    fun setData(value: List<User>) {
        users = value
        notifyDataSetChanged()
    }

    class UsersViewHolder(val binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        val binding = ItemUserLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        binding.root.setOnClickListener { itemView ->
            onUserDetails(itemView.context, itemView.tag as User)
        }

        binding.ivMoreButton.setOnClickListener { moreButtonView ->
            showPopupMenu(moreButtonView)
        }

        return UsersViewHolder(binding)

    }

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

    fun onUserDetails(context: Context, user: User) {
        Toast.makeText(context, user.toString(), Toast.LENGTH_SHORT).show()
    }

    fun showPopupMenu(moreButtonView: View) {

        val popupMenu = PopupMenu(moreButtonView.context, moreButtonView)

        popupMenu.menu.add(0, 1, Menu.NONE, "Move up")
        popupMenu.menu.add(0, 2, Menu.NONE, "Move down")
        popupMenu.menu.add(0, 3, Menu.NONE, "Delete user")

        popupMenu.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                POPUN_MENU_ID_MOVE_UP -> { onUserMove(moreButtonView.tag as User, 1) }
                POPUN_MENU_ID_MOVE_DOWN -> { onUserMove(moreButtonView.tag as User, -1) }
                POPUN_MENU_ID_DELETE_USER -> { onUserDelete(moreButtonView.tag as User)}
            }

            true
        }

    }

    fun onUserMove(user: User, moveBy: Int) {
        TODO("Not yet implemented")
    }

    fun onUserDelete(user: User) {
        TODO("Not yet implemented")
    }

}
