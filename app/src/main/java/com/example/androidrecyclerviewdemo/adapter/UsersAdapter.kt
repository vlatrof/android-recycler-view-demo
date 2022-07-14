package com.example.androidrecyclerviewdemo.adapter

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrecyclerviewdemo.R
import com.example.androidrecyclerviewdemo.databinding.ItemUserLayoutBinding
import com.example.androidrecyclerviewdemo.model.User

interface UserActionListener {
    fun onUserMove(user: User, moveBy: Int)
    fun onUserDetails(user: User)
    fun onUserDelete(user: User)
}

// наша реализация обработчика сравнения DiffUtil
class UsersDiffCallback(

    private val oldList: List<User>,
    private val newList: List<User>

    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    // are the same items with maybe different content
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id
    // are the same items with all the same content
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}

class UsersAdapter(
    private val actionListener: UserActionListener
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private val POPUN_MENU_ID_MOVE_UP = 1
    private val POPUN_MENU_ID_MOVE_DOWN = 2
    private val POPUN_MENU_ID_DELETE_USER = 3

    var users: List<User> = emptyList()
    fun setData(newUsersList: List<User>) {
        val usersDiffCallback = UsersDiffCallback(users, newUsersList)
        val difResult = DiffUtil.calculateDiff(usersDiffCallback)
        users = newUsersList
        difResult.dispatchUpdatesTo(this@UsersAdapter)
    }

    class UsersViewHolder(val binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        val binding = ItemUserLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        binding.root.setOnClickListener { itemView ->
            actionListener.onUserDetails(itemView.tag as User)
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

    private fun showPopupMenu(moreButtonView: View) {

        val user = moreButtonView.tag as User
        val userPosition = users.indexOfFirst { it.id == user.id }
        val popupMenu = PopupMenu(moreButtonView.context, moreButtonView)

        popupMenu.menu.add(0, POPUN_MENU_ID_MOVE_UP, Menu.NONE, "Move up").apply {
            isEnabled = userPosition > 0
        }

        popupMenu.menu.add(0, POPUN_MENU_ID_MOVE_DOWN, Menu.NONE, "Move down").apply {
            isEnabled = userPosition < users.size - 1
        }

        popupMenu.menu.add(0, POPUN_MENU_ID_DELETE_USER, Menu.NONE, "Delete user")

        popupMenu.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                POPUN_MENU_ID_MOVE_UP -> { actionListener.onUserMove(user, 1) }
                POPUN_MENU_ID_MOVE_DOWN -> { actionListener.onUserMove(user, -1) }
                POPUN_MENU_ID_DELETE_USER -> { actionListener.onUserDelete(user)}
            }

            return@setOnMenuItemClickListener true

        }

        popupMenu.show()

    }

}
