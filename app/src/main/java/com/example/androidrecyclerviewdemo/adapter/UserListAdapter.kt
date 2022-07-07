package com.example.androidrecyclerviewdemo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrecyclerviewdemo.R
import com.example.androidrecyclerviewdemo.model.User
import kotlinx.android.synthetic.main.item_user_layout.view.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var userList = emptyList<User>()

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        // this method is called for each element of source list (userList in this case)
        // this method returns one instance of our view holder for concrete element of list
        // this element is empty now. It will be filled with data soon in method onBindViewHolder

        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        // our view holder without concrete data that returned by method OnCreateViewHolder
        // is provided immediately to method onBindViewHolder to be filled with data
        // and be fitted in recycler view
        // @param position:
        // provides automatically as position of element in data list (userList in this case)
        // that renderers currently

        holder.itemView.tv_lastname.text = userList[position].lastName
        holder.itemView.tv_firstname.text = userList[position].firstName
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<User>) {
        this.userList = list

        // this command tells to adapter that source data (userList in this case) have been changed
        notifyDataSetChanged()
    }

}